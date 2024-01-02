package lk.ijse.Dao1;

import lk.ijse.entity.OrderDetails;
import lk.ijse.util.SQLUtil;

import java.sql.SQLException;

public interface OrderDetailsDao {

    boolean saveOrderDetail(OrderDetails entity) throws SQLException, ClassNotFoundException;
}
