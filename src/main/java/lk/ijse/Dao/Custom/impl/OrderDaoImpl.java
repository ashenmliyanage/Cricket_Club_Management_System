package lk.ijse.Dao.Custom.impl;

import lk.ijse.Dao.Custom.OrderDao;
import lk.ijse.entity.Order;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao{
    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Save(Order dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES(?,?,?)", dto.getBook_id(), dto.getMember_Id(), dto.getLocalDate());
    }

    @Override
    public Order getData(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException {
        return SQLUtil.genarate(colum,table,type);
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
    @Override
    public ArrayList<String> ChartData() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT Date FROM orders ORDER BY Date");

        ArrayList<String> date = new ArrayList<>();

        while (resultSet.next()){
            date.add(resultSet.getString(1));
        }

        return date;
    }
}
