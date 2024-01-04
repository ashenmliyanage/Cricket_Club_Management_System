package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.Model.UserDto;

import java.sql.SQLException;

public interface SettingBo extends SuperBO {
    boolean update(UserDto dto) throws SQLException, ClassNotFoundException;

    UserDto getData(String id) throws SQLException, ClassNotFoundException;

    String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException;

    boolean Save(UserDto dto) throws SQLException, ClassNotFoundException;
}
