package lk.ijse.dao.impl;

import lk.ijse.Model.OrderDto;
import lk.ijse.dao.OrderDao;
import lk.ijse.util.SQLUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    @Override
    public ArrayList<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Save(OrderDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES(?,?,?,?)", dto.getBook_id(), dto.getMember_id(), dto.getDate(), dto.getCount());
    }

    @Override
    public OrderDto getData(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean Delete(String name) throws SQLException, ClassNotFoundException {
        return false;
    }
}
