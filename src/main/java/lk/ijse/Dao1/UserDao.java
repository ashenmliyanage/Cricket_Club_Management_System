package lk.ijse.Dao1;

import lk.ijse.Model.UserDto;
import lk.ijse.entity.User;

import java.sql.SQLException;

public interface UserDao {
    User Login(String username) throws SQLException, ClassNotFoundException;
}
