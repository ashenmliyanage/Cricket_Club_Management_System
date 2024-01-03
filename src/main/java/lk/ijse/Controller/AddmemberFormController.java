package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import lk.ijse.BO.MemberBo;
import lk.ijse.BO.impl.MemberBoImpl;
import lk.ijse.Model.MemberDto;
import lk.ijse.util.ImageAutoCrop;
import lk.ijse.util.PopUPForm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;

import javafx.embed.swing.SwingFXUtils;
import lk.ijse.util.Validation;

public class AddmemberFormController {


    public TextField FullName;
    public ComboBox<String> Position;
    public TextField Age;
    public DatePicker Birthday;
    public TextField Email;
    public TextField Address;
    public Label label;
    @FXML
    private ImageView image;

    InputStream inputStream;

    MemberBo memberDao = new MemberBoImpl();
    @FXML
    void SavebtnOnActhion(ActionEvent event) {
        String Name = FullName.getText();
        String bod = String.valueOf(Birthday.getValue());
        String posithion = Position.getValue();
        int Age = Integer.parseInt(this.Age.getText());
        String Email = this.Email.getText();
        String Address = this.Address.getText();

        boolean valid = Validation.MemberValid(Name, bod, Age, Email, Address, inputStream);
        try {
            String Id = memberDao.generateId("Member_ID", "member", "M0");
            try {
                System.out.println(valid);
                if (valid) {
                    System.out.println(valid);
                    boolean isSaved = memberDao.Save(new MemberDto(Id, Name,posithion, bod, Age, Email, Address, inputStream));
                    clearData();
                    initialize();
                    System.out.println(isSaved);
                    if (isSaved) {
                        new Alert(Alert.AlertType.INFORMATION,"Save Successful").show();
                    }
                }
                else {
                    new Alert(Alert.AlertType.INFORMATION,"Wrong Data Entered").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearData() {
        FullName.clear();
        Position.setValue(null);
        Birthday.setValue(null);
        Age.clear();Email.clear();
        Address.clear();
        image.setImage(null);

    }

    public void initialize() throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/front/"),14);
        ObservableList<String> observableArray = FXCollections.observableArrayList(
                "All Rounder",
                "Batsman",
                "Bowler"
        );
        Position.setItems(observableArray);
    }

    public void backbtnOnActhion(ActionEvent actionEvent) {
        PopUPForm.stage.close();
    }

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

    public void NamebtnOnActhion(ActionEvent actionEvent) {
        label.setText(FullName.getText());
    }
}
