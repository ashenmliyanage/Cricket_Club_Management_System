package lk.ijse.Dao1;

import lk.ijse.entity.OrderDetails;
import lk.ijse.util.SQLUtil;

import java.sql.SQLException;

public class OrderDetailDaoImpl implements OrderDetailsDao{
    @Override
    public boolean saveOrderDetail(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orderdetails VALUES (?,?,?)",
                entity.getOrderId(),
                entity.getItemCode(),
                entity.getQty());
    }
}
