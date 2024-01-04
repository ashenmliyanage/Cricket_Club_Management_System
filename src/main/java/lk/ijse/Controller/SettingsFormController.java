package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import lk.ijse.BO.custom.SettingBo;
import lk.ijse.BO.custom.impl.LoginBoImpl;
import lk.ijse.BO.custom.impl.SettingBoImpl;
import lk.ijse.Model.UserDto;
import lk.ijse.util.ImageAutoCrop;
import lk.ijse.util.PopUPForm;
import lk.ijse.util.Validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SettingsFormController implements Initializable {

    SettingBo userDao = new SettingBoImpl();

    public ImageView image;
    public TextField N_userName;
    public TextField NewPassword;
    public TextField name;
    public TextField address;
    public TextField Mail;
    public TextField Age;

    InputStream inputStream = null;
    public void AddBtnOnActhion(ActionEvent actionEvent) {

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

    public static boolean Success = true;

    private String id = LoginBoImpl.Id;

    public void updateBtnOnActhion(ActionEvent actionEvent) {

        String newUserName =  this.N_userName.getText();
        String newPassword = this.NewPassword.getText();
        String Name = this.name.getText();
        String Address = this.address.getText();
        String Mail = this.Mail.getText();
        int Age = Integer.parseInt(this.Age.getText());

        //boolean valid = Validation.MemberValid();
        boolean valid = Validation.UserValid(Name,Address,Mail,Age,inputStream);

        if (valid){
            try {

                boolean save = userDao.update(new UserDto(id, Name, Address, Age, Mail, newUserName, newPassword, inputStream));

                if (save){
                    new Alert(Alert.AlertType.INFORMATION,"Updated").show();
                    Success = true;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        laodData();
    }

    private void laodData() {
        try {
            UserDto userDto = userDao.getData(this.id);
            if (userDto.getName() == null){
                Success = false;
                this.name.setText(null);
            }
            else {
                this.name.setText(userDto.getName());
            }
            if (userDto.getAddress() == null){
                this.address.setText("");
            }
            else {
                this.address.setText(userDto.getAddress());
            }
            this.Age.setText(String.valueOf(userDto.getAge()));
            this.Mail.setText(userDto.getMail());
            this.N_userName.setText(userDto.getUsername());
            this.NewPassword.setText(userDto.getPassword());
            if (userDto.getImage() == null){
                this.image.setImage(null);
            }
            else {
                this.image.setImage(new Image(userDto.getImage()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMemberOnActhion(ActionEvent actionEvent) {
        PopUPForm.openFXMLPopupForm("AddUser.fxml",null);
    }
}
