package lk.ijse.BO.custom;

import java.sql.SQLException;

public interface LoginBo {
    String login(String Username, String Password) throws SQLException, ClassNotFoundException;
}
