package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import lk.ijse.BO.LoginBo;
import lk.ijse.BO.impl.LoginBoImpl;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.PasswordField;

public class LoginFormController implements Initializable {
//Main pass - Cricket@2023
    public Label createAcc;
    public AnchorPane ChangePane;
    @FXML
    private PasswordField Password;

    @FXML
    private TextField Username;
    @FXML
    private TextField watch;

    LoginBo loginBo = new LoginBoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        watch.setVisible(false);
        Font.loadFont(getClass().getResourceAsStream("/front/AnonymousPro-Bold.ttf"),14);
        Font.loadFont(getClass().getResourceAsStream("/front/Roboto-Regular.ttf"),14);
        Font.loadFont(getClass().getResourceAsStream("/front/Roboto-Bold.ttf"),14);
    }

    public void createbtnOnActhion(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/View/CreateAccountForm.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);
    }

    public void forgetbtnOnActhion(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/View/ForgetPassword.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);
    }

    public void loginOnActhion(ActionEvent actionEvent) throws IOException {

        try {
            String out = loginBo.login(Username.getText(),Password.getText());
            switch (out){
                case "Oky":
                    Parent parent = FXMLLoader.load(this.getClass().getResource("/View/MainDashbordForm.fxml"));
                    this.ChangePane.getChildren().clear();
                    this.ChangePane.getChildren().add(parent);
                    break;

                case "Username":
                    new Alert(Alert.AlertType.INFORMATION,"Username is not correct").show();
                    this.Username.setStyle("-fx-border-color: red;-fx-border-radius: 38px;");
                    break;

                case "Pass":
                    this.Username.setStyle("-fx-border-color: rgba(0,0,0,0);-fx-border-radius: 38px;");
                    Password.setStyle("-fx-border-color: red;-fx-border-radius: 38px;");
                    watch.setStyle("-fx-border-color: red;-fx-border-radius: 38px;");
                    new Alert(Alert.AlertType.INFORMATION,"Password is not correct").show();
                    break;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    boolean flag = false;
    public void watchonActhion(MouseEvent mouseEvent) {
        String pass = Password.getText();

        if (!flag){
            Password.setVisible(false);
            watch.setVisible(true);
            watch.setText(pass);
            flag = true;
            System.out.println(true);
            return;
        }
        if (flag){
            Password.setVisible(true);
            watch.setVisible(false);
            flag = false;
            System.out.println(false);
        }

    }
}
