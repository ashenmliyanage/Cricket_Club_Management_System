package lk.ijse.Dao.Custom.impl;

import lk.ijse.Dao.Custom.UserDao;
import lk.ijse.entity.User;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
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

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Update User SET Name = ? , Address = ?, Age = ?, Mail = ?, Username = ?, Password = ?, Image = ? Where User_Id = ?",
                dto.getName(),dto.getAddress(),dto.getAge(),dto.getMail(),dto.getUsername(),dto.getPassword(),dto.getImage(),dto.getUser_id());
    }

    @Override
    public User getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM User WHERE User_Id = ?", id);

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

    @Override
    public String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException {
        return SQLUtil.genarate(colum,table,type);
    }

    @Override
    public boolean Save(User dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO User VALUES(?,?,?,?,?,?,?,?)",
                dto.getUser_id(),dto.getName(),dto.getAddress(),dto.getAge(),dto.getMail(),dto.getUsername(),dto.getPassword(),dto.getImage());
    }
}
