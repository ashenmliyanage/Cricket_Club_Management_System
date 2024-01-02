package lk.ijse.BO.impl;

import lk.ijse.BO.BookBo;
import lk.ijse.Dao1.*;
import lk.ijse.Model.OrderDto;
import lk.ijse.TM.bookTm;
import lk.ijse.entity.Order;
import lk.ijse.entity.OrderDetails;
import lk.ijse.util.TransactionUtil;

import java.sql.SQLException;

public class BookBoImpl implements BookBo {
    OrderDao orderDao = new OrderDaoImpl();

    StockDao stockManageDao = new StockDaoImpl();

    OrderDetailsDao orderDetailsDao = new OrderDetailDaoImpl();


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
}
