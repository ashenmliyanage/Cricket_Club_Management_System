package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.Dao.Custom.MemberDao;
import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.SuperDAO;

import javax.mail.MessagingException;
import java.sql.SQLException;

public interface ForgetPasswordBo extends SuperBO {

    boolean SendMail(String mail) throws MessagingException, SQLException, ClassNotFoundException;
}
