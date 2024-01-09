package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.CreateAccountBo;
import lk.ijse.Dao.Custom.UserDao;
import lk.ijse.Dao.Custom.impl.UserDaoImpl;
import lk.ijse.Dao.DAOFactory;
import lk.ijse.Model.UserDto;
import lk.ijse.entity.User;

import java.sql.SQLException;

public class CreateAccountBoImpl implements CreateAccountBo {
    UserDao userDao = (UserDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.User);

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
