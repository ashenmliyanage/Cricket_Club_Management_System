package lk.ijse.BO;

import lk.ijse.Model.OrderDto;

import java.sql.SQLException;

public interface BookBo {
    boolean Save(OrderDto dto) throws SQLException, ClassNotFoundException;
}
