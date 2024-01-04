package lk.ijse.Dao.Custom;

import lk.ijse.entity.OrderDetails;

import java.sql.SQLException;

public interface OrderDetailsDao {

    boolean saveOrderDetail(OrderDetails entity) throws SQLException, ClassNotFoundException;
}
