package lk.ijse.Dao1;

import lk.ijse.Model.UserDto;
import lk.ijse.entity.User;

import java.sql.SQLException;

public interface UserDao {
    User Login(String username) throws SQLException, ClassNotFoundException;

    boolean update(User dto) throws SQLException, ClassNotFoundException;

    User getData(String id) throws SQLException, ClassNotFoundException;

    String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException;

    boolean Save(User dto) throws SQLException, ClassNotFoundException;
}
