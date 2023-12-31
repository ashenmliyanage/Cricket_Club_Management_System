package lk.ijse.dao.impl;

import lk.ijse.Model.StockDto;
import lk.ijse.Model.UserDto;
import lk.ijse.dao.UserDao;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoimpl implements UserDao {

    @Override
    public ArrayList<UserDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException {
        return SQLUtil.genarate(colum,table,type);
    }

    @Override
    public boolean Save(UserDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO User VALUES(?,?,?,?,?,?,?,?)",
                dto.getUser_id(),dto.getName(),dto.getAddress(),dto.getAge(),dto.getMail(),dto.getUsername(),dto.getPassword(),dto.getImage());
    }

    @Override
    public UserDto getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM User WHERE User_Id = ?", id);

        UserDto dto = null;

        if (resultSet.next()) {
            dto = new UserDto(
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
    public boolean update(UserDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Update User SET Name = ? , Address = ?, Age = ?, Mail = ?, Username = ?, Password = ?, Image = ? Where User_Id = ?",
                dto.getName(),dto.getAddress(),dto.getAge(),dto.getMail(),dto.getUsername(),dto.getPassword(),dto.getImage(),dto.getUser_id());
    }

    @Override
    public boolean Delete(String name) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public UserDto Login(String username) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM User WHERE Username = ?", username);

        UserDto dto = null;

        if (resultSet.next()) {
            dto = new UserDto(
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
