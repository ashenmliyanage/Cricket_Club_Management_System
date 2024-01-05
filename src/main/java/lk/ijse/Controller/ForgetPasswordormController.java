package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.ForgetPasswordBo;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ForgetPasswordormController implements Initializable {
    public AnchorPane ChangePane;
    public TextField Text;

    ForgetPasswordBo forgetPasswordBo = (ForgetPasswordBo) BOFactory.getInstance().getBO(BOFactory.BOType.Forget);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font.loadFont(getClass().getResourceAsStream("/front/AnonymousPro-Bold.ttf"),14);
        Font.loadFont(getClass().getResourceAsStream("/front/Roboto-Regular.ttf"),14);
        Font.loadFont(getClass().getResourceAsStream("/front/Roboto-Bold.ttf"),14);
    }

    public void backtnOnActhion(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/View/LoaginForm.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);
    }

    public void send(ActionEvent actionEvent) throws IOException, MessagingException, SQLException, ClassNotFoundException {
            boolean mail = forgetPasswordBo.SendMail(Text.getText());
            if (mail){
                Parent parent = FXMLLoader.load(this.getClass().getResource("/View/OTPForm.fxml"));
                this.ChangePane.getChildren().clear();
                this.ChangePane.getChildren().add(parent);
            }
            else {
                new Alert(Alert.AlertType.INFORMATION,"Wrong Mail").show();
            }

    }
}
