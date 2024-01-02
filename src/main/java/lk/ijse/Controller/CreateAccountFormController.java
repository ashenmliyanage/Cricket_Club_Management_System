package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.CreateAccountBo;
import lk.ijse.BO.impl.CreateAccountBoImpl;
import lk.ijse.Model.UserDto;
import lk.ijse.dao.UserDao;
import lk.ijse.dao.impl.UserDaoimpl;
import lk.ijse.util.Validation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateAccountFormController implements Initializable {

    @FXML
    private PasswordField ConPass;

    @FXML
    private TextField Email;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField UserName;

    public AnchorPane ChangePane;

    public static boolean sign = false;
    public static String Uid;

    CreateAccountBo userDao = new CreateAccountBoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font.loadFont(getClass().getResourceAsStream("/front/AnonymousPro-Bold.ttf"),14);
        Font.loadFont(getClass().getResourceAsStream("/front/Roboto-Regular.ttf"),14);
        Font.loadFont(getClass().getResourceAsStream("/front/Roboto-Bold.ttf"),14);
    }

    public void createbtnOnActhion(ActionEvent actionEvent) throws IOException {

        if (valid()){
            sign = true;
            Parent parent = FXMLLoader.load(this.getClass().getResource("/View/LoaginForm.fxml"));
            this.ChangePane.getChildren().clear();
            this.ChangePane.getChildren().add(parent);
        }
    }

    boolean valid(){
        boolean validEmail = Validation.isValidEmail(Email.getText());

        if (validEmail){
            try {
                String id = userDao.generateId("User_Id", "User", "U0");
                Uid = id;
                if (Password.getText().equals(ConPass.getText())) {
                    boolean saved = userDao.Save(new UserDto(id, null, null, 0, Email.getText(), UserName.getText(), Password.getText(), null));
                    new Alert(Alert.AlertType.INFORMATION,"Account creation is successfully").show();
                    return true;
                }else {
                    new Alert(Alert.AlertType.INFORMATION,"Password is not match").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            new Alert(Alert.AlertType.INFORMATION,"Email is not valid").show();
        }
        return false;
    }
}
