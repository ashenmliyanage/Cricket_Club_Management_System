package lk.ijse.BO.impl;

import lk.ijse.BO.SettingBo;
import lk.ijse.Dao1.UserDao;
import lk.ijse.Dao1.UserDaoImpl;
import lk.ijse.Model.UserDto;
import lk.ijse.entity.User;

import java.sql.SQLException;

public class SettingBoImpl implements SettingBo {
    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean update(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDao.update(new User(
                dto.getUser_id(),
                dto.getName(),
                dto.getAddress(),
                dto.getAge(),
                dto.getMail(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getImage()
        ));
    }

    @Override
    public UserDto getData(String id) throws SQLException, ClassNotFoundException {
        User data = userDao.getData(id);

        return new UserDto(
                data.getUser_id(),
                data.getName(),
                data.getAddress(),
                data.getAge(),
                data.getMail(),
                data.getUsername(),
                data.getPassword(),
                data.getImage()
        );
    }

    @Override
    public String generateId(String colum, String table, String type) throws SQLException, ClassNotFoundException {
        return userDao.generateId(colum,table,type);
    }

    @Override
    public boolean Save(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDao.Save(new User(
                dto.getUser_id(),
                dto.getName(),
                dto.getAddress(),
                dto.getAge(),
                dto.getMail(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getImage()
        ));
    }
}
