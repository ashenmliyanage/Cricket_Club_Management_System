package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.LoginBo;
import lk.ijse.Dao.Custom.UserDao;
import lk.ijse.Dao.Custom.impl.UserDaoImpl;
import lk.ijse.entity.User;

import java.sql.SQLException;

public class LoginBoImpl implements LoginBo {

    public static String Id;
    @Override
    public String login(String Username, String Password) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDaoImpl();
        User login = userDao.Login(Username);

        if (login != null){
            if (login.getPassword().equals(Password)){
                Id = login.getUser_id();
                return "Oky";
            }
            else {
                return "Pass";
            }
        }
        else {
            return "Username";
        }
    }
}
