package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.BookBo;
import lk.ijse.Dao.Custom.*;
import lk.ijse.Dao.Custom.impl.MemberDaoImpl;
import lk.ijse.Dao.Custom.impl.OrderDaoImpl;
import lk.ijse.Dao.Custom.impl.OrderDetailDaoImpl;
import lk.ijse.Dao.Custom.impl.StockDaoImpl;
import lk.ijse.Dao.DAOFactory;
import lk.ijse.Model.MemberDto;
import lk.ijse.Model.OrderDto;
import lk.ijse.Model.StockDto;
import lk.ijse.TM.bookTm;
import lk.ijse.entity.Member;
import lk.ijse.entity.Order;
import lk.ijse.entity.OrderDetails;
import lk.ijse.entity.Stock;
import lk.ijse.util.TransactionUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBoImpl implements BookBo {
    OrderDao orderDao = (OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Order);

    StockDao stockManageDao = (StockDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Stock);

    OrderDetailsDao orderDetailsDao = (OrderDetailsDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.OrderDetails);
    MemberDao memberDao = (MemberDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Member);



    @Override
    public boolean Save(OrderDto dto) throws SQLException, ClassNotFoundException {
        TransactionUtil.startTransaction();

        String ID = orderDao.generateId("Book_id","orders","B");

        boolean saved = orderDao.Save(new Order(ID, dto.getMember_Id(), dto.getLocalDate(),dto.getQty(), dto.getTmList()));

        if (!saved){
            TransactionUtil.rollBack();
            return false;
        }

        for (bookTm tm : dto.getTmList()){
            if (!orderDetailsDao.Save(new OrderDetails(ID,tm.getItemCode(),tm.getCount()))){
                TransactionUtil.rollBack();
                return false;
            }
            int count = Integer.parseInt(tm.getCount());
            if (!stockManageDao.update(count,tm.getQty(),tm.getItemCode())){
                TransactionUtil.rollBack();
                return false;
            }
        }
        TransactionUtil.endTransaction();
        return true;
    }
    @Override
    public StockDto getData(String ItemCode) throws SQLException, ClassNotFoundException {
        Stock stock = stockManageDao.getData(ItemCode);
        return new StockDto(stock.getCode(),stock.getDomain(),stock.getDomain_Name(),stock.getType(),stock.getDate(),stock.getMember(),stock.getCount());
    }
    @Override
    public List<StockDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> all = stockManageDao.getAll();

        ArrayList<StockDto> send = new ArrayList<>();
        for (Stock dto : all) {
            send.add(new StockDto(
                    dto.getCode(),
                    dto.getDomain(),
                    dto.getDomain_Name(),
                    dto.getType(),
                    dto.getDate(),
                    dto.getMember(),
                    dto.getCount()
            ));
        }

        return send;
    }
    @Override
    public MemberDto getMData(String Id) throws SQLException, ClassNotFoundException{
        Member member = memberDao.getData(Id);

        return new MemberDto(member.getMember_id(),member.getFull_name(), member.getPosition(),member.getBod(), member.getAge(), member.getEmail(), member.getAddress(),member.getImage());
    }
}
