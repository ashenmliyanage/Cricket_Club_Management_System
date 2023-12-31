package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForgetPasswordormController implements Initializable {
    public AnchorPane ChangePane;

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
}
