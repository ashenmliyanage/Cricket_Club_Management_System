package lk.ijse.Dao.Custom;

import lk.ijse.entity.Order;
import lk.ijse.entity.Stock;
import lk.ijse.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDao extends CrudUtil<Order> {

    ArrayList<String> ChartData() throws SQLException, ClassNotFoundException;
}
