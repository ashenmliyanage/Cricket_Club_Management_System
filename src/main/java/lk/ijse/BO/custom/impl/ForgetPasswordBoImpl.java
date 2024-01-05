package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.ForgetPasswordBo;
import lk.ijse.Dao.Custom.MemberDao;
import lk.ijse.Dao.Custom.UserDao;
import lk.ijse.Dao.DAOFactory;
import lk.ijse.Email.EmailController;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.Random;

public class ForgetPasswordBoImpl implements ForgetPasswordBo {
    UserDao memberDao = (UserDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.User);

    public static int otp = 0;
    public static String Mail = "";

    @Override
    public boolean SendMail(String mail) throws MessagingException, SQLException, ClassNotFoundException {
        String UserId = memberDao.getUserId(mail);

        if (UserId != null) {
            System.out.println(UserId);
            LoginBoImpl.Id = UserId;
            Mail = mail;
            otp = new Random().nextInt(9000) + 1000;
            EmailController.sendEmail(mail, otp);
            return true;
        }
        else {
            return false;
        }
    }
}
