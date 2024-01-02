package lk.ijse.BO;

import lk.ijse.Model.UserDto;

import java.sql.SQLException;

public interface SettingBo {
    boolean update(UserDto dto) throws SQLException, ClassNotFoundException;

    UserDto getData(String id) throws SQLException, ClassNotFoundException;

    String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException;

    boolean Save(UserDto dto) throws SQLException, ClassNotFoundException;
}
