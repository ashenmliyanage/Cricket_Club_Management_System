package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.Model.UserDto;

import java.sql.SQLException;

public interface CreateAccountBo extends SuperBO {
    String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException;

    boolean Save(UserDto dto) throws SQLException, ClassNotFoundException;
}
