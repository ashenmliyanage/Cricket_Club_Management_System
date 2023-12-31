package lk.ijse.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainDashbordFormController{


    public ImageView logoutHaving;
    public ImageView logOut;
    @FXML
    private AnchorPane ChangePane;

    @FXML
    private ImageView DashbordClick;

    @FXML
    private ImageView Member;

    @FXML
    private ImageView Memberhaving;

    @FXML
    private ImageView book;

    @FXML
    private ImageView bookclick;

    @FXML
    private ImageView bookhaving;

    @FXML
    private ImageView bookhaving111;

    @FXML
    private ImageView dashbordhaving;

    @FXML
    private ImageView dashicon;

    @FXML
    private ImageView memberClick;

    @FXML
    private ImageView settingsbtnhaving;

    @FXML
    private ImageView settingsclick;

    @FXML
    private ImageView stocckclick;

    @FXML
    private ImageView stockHaving;

    @FXML
    private ImageView stockIcom;

    @FXML
    private ImageView settings;


    public void initialize() throws IOException {
        if (CreateAccountFormController.sign){
            new Alert(Alert.AlertType.WARNING,"Please complete your account").show();
        }
        DashbordLoad();
    }

    @FXML
    void MouseEntered(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            switch (icon.getId()){
                case "dashicon":
                    dashbordhaving.setVisible(true);
                    break;
                case "Member":
                    Memberhaving.setVisible(true);
                    break;
                case "stockIcom":
                    stockHaving.setVisible(true);
                    break;
                case "book":
                    bookhaving.setVisible(true);
                    break;
                case "settings":
                    settingsbtnhaving.setVisible(true);
                    break;
                case "logOut":
                    logoutHaving.setVisible(true);
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();
        }
    }

    @FXML
    void MouseExited(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            switch (icon.getId()){
                case "dashicon":
                    dashbordhaving.setVisible(false);
                case "Member":
                    Memberhaving.setVisible(false);
                case "stockIcom":
                    stockHaving.setVisible(false);
                case "book":
                    bookhaving.setVisible(false);
                case "settings":
                    settingsbtnhaving.setVisible(false);
                    break;
                case "logOut":
                    logoutHaving.setVisible(false);
                    break;
            }
            icon.setEffect(null);
        }
    }

    @FXML
    void dashboardbtnOnActhion(MouseEvent event) throws IOException {
        DashbordLoad();
    }

    @FXML
    void memberbtnOnActhion(MouseEvent event) throws IOException {
        MemberForm();
    }

    void DashbordLoad(){
        try {
            Click(DashbordClick.getId());
            Parent parent = FXMLLoader.load(this.getClass().getResource("/View/Dashbord.fxml"));
            this.ChangePane.getChildren().clear();
            this.ChangePane.getChildren().add(parent);
        } catch (IOException e) {

        }
    }

    void MemberForm()throws IOException{
        try {
            Click(memberClick.getId());
            Parent parent = FXMLLoader.load(this.getClass().getResource("/View/MemberManangeForm.fxml"));
            this.ChangePane.getChildren().clear();
            this.ChangePane.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stockbtnOnActhion(MouseEvent mouseEvent) throws IOException {
        try {
            Click(stocckclick.getId());
            Parent parent = FXMLLoader.load(this.getClass().getResource("/View/StockMaageForm.fxml"));
            this.ChangePane.getChildren().clear();
            this.ChangePane.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void bookbtnOnActhion(MouseEvent event) throws IOException {
        try {
            Click(bookclick.getId());
            Parent parent = FXMLLoader.load(this.getClass().getResource("/View/BookForm.fxml"));
            this.ChangePane.getChildren().clear();
            this.ChangePane.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void settingbtnOnActhion(MouseEvent mouseEvent) throws IOException {
        try {
            Click(settingsclick.getId());
            Parent parent = FXMLLoader.load(this.getClass().getResource("/View/SettingsForm.fxml"));
            this.ChangePane.getChildren().clear();
            this.ChangePane.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logOutbtnOnActhion(MouseEvent mouseEvent) {
        if (SettingsFormController.Success){
            System.exit(0);
        }
        new Alert(Alert.AlertType.WARNING,"Can't log out because ,\n Account creation is not Successful").show();
    }

    void Click(String name){
        ObservableList<ImageView> img = FXCollections.observableArrayList(
                DashbordClick,
                memberClick,
                stocckclick,
                bookclick,
                settingsclick
        );

        for (int i = 0; i < img.size(); i++) {
            if (img.get(i).getId().equals(name)){
                img.get(i).setVisible(true);
            }
            else {
                img.get(i).setVisible(false);
            }
        }
    }
}
