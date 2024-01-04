package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;

import java.sql.SQLException;

public interface LoginBo extends SuperBO {
    String login(String Username, String Password) throws SQLException, ClassNotFoundException;
}
