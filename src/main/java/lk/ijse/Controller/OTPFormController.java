package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.ForgetPasswordBo;
import lk.ijse.BO.custom.impl.ForgetPasswordBoImpl;
import lk.ijse.Dao.DAOFactory;
import lk.ijse.Email.EmailController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OTPFormController implements Initializable {


    @FXML
    private AnchorPane ChangePane;
    @FXML
    private TextField fild;

    int Otp = 0;

    ForgetPasswordBo forgetPasswordBo = (ForgetPasswordBo) BOFactory.getInstance().getBO(BOFactory.BOType.Forget);

    @FXML
    void backbtnOnActhion(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/View/LoaginForm.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);

    }

    @FXML
    void reSendOnActhion(ActionEvent event) {
        try {
            boolean sent = forgetPasswordBo.SendMail(ForgetPasswordBoImpl.Mail);
            if (sent){
                new Alert(Alert.AlertType.INFORMATION,"Mail send").show();
                initialize(null,null);
            }
        } catch (MessagingException e) {
            new Alert(Alert.AlertType.INFORMATION,"Wrong Mail").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,"Wrong Mail").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION,"Wrong Mail").show();
        }
    }

    @FXML
    void verifyOnActhion(ActionEvent event) throws IOException {
        String otp = String.valueOf(Otp);
        if (fild.getText().equals(otp)){
            Parent parent = FXMLLoader.load(this.getClass().getResource("/View/MainDashbordForm.fxml"));
            this.ChangePane.getChildren().clear();
            this.ChangePane.getChildren().add(parent);
        }
        else {
            new Alert(Alert.AlertType.INFORMATION,"Wrong OTP").show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Otp = ForgetPasswordBoImpl.otp;
        Font.loadFont(getClass().getResourceAsStream("/front/AnonymousPro-Bold.ttf"),14);
        Font.loadFont(getClass().getResourceAsStream("/front/Roboto-Regular.ttf"),14);
        Font.loadFont(getClass().getResourceAsStream("/front/Roboto-Bold.ttf"),14);
        fild.setStyle("-fx-background-color: rgba(0,0,0,0);" +
                "-fx-font-family: 'Roboto';" +
                "-fx-font-weight: 500;" +
                "-fx-font-size: 65px;" +
                "-fx-alignment: center");
    }
}
