package lk.ijse.Dao.Custom.impl;

import lk.ijse.Dao.Custom.OrderDao;
import lk.ijse.entity.Order;
import lk.ijse.util.SQLUtil;

import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean Save(Order dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES(?,?,?)", dto.getBook_id(), dto.getMember_Id(), dto.getLocalDate());
    }

    @Override
    public String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException {
        return SQLUtil.genarate(colum,table,type);
    }
}
