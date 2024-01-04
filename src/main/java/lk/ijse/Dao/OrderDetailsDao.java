package lk.ijse.Dao;

import lk.ijse.entity.OrderDetails;

import java.sql.SQLException;

public interface OrderDetailsDao {

    boolean saveOrderDetail(OrderDetails entity) throws SQLException, ClassNotFoundException;
}
