package lk.ijse.BO.impl;

import lk.ijse.BO.BookBo;
import lk.ijse.Dao1.*;
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
    OrderDao orderDao = new OrderDaoImpl();

    StockDao stockManageDao = new StockDaoImpl();

    OrderDetailsDao orderDetailsDao = new OrderDetailDaoImpl();
    MemberDao memberDao = new MemberDaoImpl();



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
            if (!orderDetailsDao.saveOrderDetail(new OrderDetails(ID,tm.getItemCode(),tm.getCount()))){
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
