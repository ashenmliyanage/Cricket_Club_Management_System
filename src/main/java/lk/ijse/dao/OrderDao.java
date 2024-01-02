package lk.ijse.dao;

import lk.ijse.Model.OrderDto;
import lk.ijse.entity.Order;

import java.sql.SQLException;

public interface OrderDao extends CrudDao<OrderDto>{
    boolean Save(Order dto) throws SQLException, ClassNotFoundException;
}
