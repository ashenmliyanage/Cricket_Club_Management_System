package lk.ijse.BO;

import java.sql.SQLException;

public interface LoginBo {
    String login(String Username, String Password) throws SQLException, ClassNotFoundException;
}
