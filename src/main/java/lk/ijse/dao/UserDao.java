package lk.ijse.dao;

import lk.ijse.Model.UserDto;

import java.sql.SQLException;

public interface UserDao extends CrudDao<UserDto>{
    UserDto Login(String username) throws SQLException, ClassNotFoundException;
}
