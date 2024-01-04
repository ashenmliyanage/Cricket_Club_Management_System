package lk.ijse.Dao.Custom;

import lk.ijse.entity.User;
import lk.ijse.util.CrudUtil;

import java.sql.SQLException;

public interface UserDao extends CrudUtil<User> {
    User Login(String username) throws SQLException, ClassNotFoundException;

}
