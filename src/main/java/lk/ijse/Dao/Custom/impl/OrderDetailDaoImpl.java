package lk.ijse.Dao.Custom.impl;

import lk.ijse.Dao.Custom.OrderDetailsDao;
import lk.ijse.entity.OrderDetails;
import lk.ijse.util.SQLUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDaoImpl implements OrderDetailsDao{
    @Override
    public boolean update(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Save(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orderdetails VALUES (?,?,?)",
                entity.getOrderId(),
                entity.getItemCode(),
                entity.getQty());
    }

    @Override
    public OrderDetails getData(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
