package lk.ijse.Dao1;

import lk.ijse.Model.UserDto;
import lk.ijse.entity.User;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao{
    @Override
    public User Login(String username) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM User WHERE Username = ?", username);

        User dto = null;

        if (resultSet.next()) {
            dto = new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getAsciiStream(8)
            );
        }
        System.out.println(dto);
        return dto;
    }


}
