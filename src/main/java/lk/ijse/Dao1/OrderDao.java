package lk.ijse.Dao1;

import lk.ijse.entity.Order;

import java.sql.SQLException;

public interface OrderDao {
    boolean Save(Order dto) throws SQLException, ClassNotFoundException;

    String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException;
}
