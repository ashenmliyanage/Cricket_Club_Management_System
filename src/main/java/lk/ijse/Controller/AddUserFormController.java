package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import lk.ijse.BO.SettingBo;
import lk.ijse.BO.impl.SettingBoImpl;
import lk.ijse.Model.UserDto;
import lk.ijse.dao.UserDao;
import lk.ijse.dao.impl.UserDaoimpl;
import lk.ijse.util.ImageAutoCrop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

public class AddUserFormController {

    @FXML
    private TextField Address;

    @FXML
    private TextField Age;

    @FXML
    private TextField Email;

    @FXML
    private ImageView image;

    @FXML
    private Label label;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    SettingBo userDao = new SettingBoImpl();
    @FXML
    void NamebtnOnActhion(ActionEvent event) {
        label.setText(name.getText());
    }

    @FXML
    void SavebtnOnActhion(ActionEvent event) {
        int Age = Integer.parseInt(this.Age.getText());
        try {
            String Id = userDao.generateId("User_Id", "user", "U0");
            boolean save = userDao.Save(new UserDto(Id, name.getText(), Address.getText(), Age, this.Email.getText(), username.getText(), password.getText(), inputStream));

            if (save){
                new Alert(Alert.AlertType.INFORMATION,"Saved").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    InputStream inputStream;

    @FXML
    void importImage(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files","*.jpg","*jpeg","*png");
        fileChooser.getExtensionFilters().add(filter);
        File selectFile = fileChooser.showOpenDialog(null);

        Image TempImage;
        if (selectFile != null){
            String path = selectFile.getAbsolutePath();
            Image image = new Image("file:"+path);
            ImageView imageView = new ImageView(image);
            TempImage = image;
            image = ImageAutoCrop.autoCropCenter(image,1500,1500);
            this.image.setImage(image);
            this.image.setPreserveRatio(true);
            this.image.setFitWidth(275);
            this.image.setFitHeight(275);

            //___________________________________________________________________________

            File file = new File(path);
            try {
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
