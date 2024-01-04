package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.MemberBo;
import lk.ijse.BO.custom.impl.MemberBoImpl;
import lk.ijse.Model.MemberDto;
import lk.ijse.util.PopUPForm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadMemberFormController implements Initializable {

    public ImageView secondImage;
    public ImageView thirdImage;
    public Label foMemberName;
    public ImageView fourthImage;
    public JFXButton fDelete;
    public JFXButton sDelete;
    public JFXButton tDelete;
    public JFXButton foDelete;
    @FXML
    private ImageView firstImage;

    @FXML
    private Label fMembername;

    @FXML
    private Label sMemberName;

    @FXML
    private Label tMemberName;

    MemberBo memberDao = (MemberBo) BOFactory.getInstance().getBO(BOFactory.BOType.Member);

    public void setData(ArrayList<MemberDto> list){

//        fMembername = null;
//        sMemberName = null;
//        tMemberName = null;
//        foMemberName = null;

        switch (list.size()){
            case 4:
                fourthImage.setImage(new Image(list.get(3).getImage()));
                foMemberName.setText(list.get(3).getFull_name());
            case 3:
                thirdImage.setImage(new Image(list.get(2).getImage()));
                tMemberName.setText(list.get(2).getFull_name());
            case 2:
                secondImage.setImage(new Image(list.get(1).getImage()));
                sMemberName.setText(list.get(1).getFull_name());
            case 1:
                firstImage.setImage(new Image(list.get(0).getImage()));
                fMembername.setText(list.get(0).getFull_name());
        }

        if (foMemberName.getText().equals("")){
            foDelete.setText("Add Member");
            foDelete.setStyle("-fx-background-color: #3FFF20");
        }
        if (tMemberName.getText().equals("")){
            tDelete.setText("Add Member");
            tDelete.setStyle("-fx-background-color: #3FFF20");
        }
        if (sMemberName.getText().equals("")){
            sDelete.setText("Add Member");
            sDelete.setStyle("-fx-background-color: #3FFF20");
        }
        if (fMembername.getText().equals("")){
            fDelete.setText("Add Member");
            fDelete.setStyle("-fx-background-color: #3FFF20");
        }
    }

    public static Stage popupStage;

    public void initialize() throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/front/"),14);
        firstImage.setImage(null);
        secondImage.setImage(null);
        thirdImage.setImage(null);
        fourthImage.setImage(null);
    }

    @FXML
    void MouseEnterd(MouseEvent event) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
    }

    public void UpdatememberbtnOnActhion(MouseEvent mouseEvent) {
        openFXMLPopupForm(fMembername.getText());
    }
    public static void openFXMLPopupForm(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(PopUPForm.class.getResource("/View/UpdatememberForm.fxml"));
            Parent root = loader.load();
            UpdatememberFormController controller = loader.getController();
            controller.initData(name);
            popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("FXML Popup Form");
            popupStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void MouseExit(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
        }
    }

    public void secountClick(MouseEvent event) {
        openFXMLPopupForm(sMemberName.getText());
    }

    public void TheirdClick(MouseEvent event) {
        openFXMLPopupForm(tMemberName.getText());
    }

    public void fothClick(MouseEvent event) {
        openFXMLPopupForm(foMemberName.getText());
    }

    public void fDeleteOnActhion(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(fDelete.getText().equals("Add Member")){
            PopUPForm.openFXMLPopupForm("AddmemberForm.fxml",popupStage);
        }
        else {
            boolean delete = memberDao.Delete(fMembername.getText());

            if (delete){
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
            }
        }
    }

    public void sDeleteonActhion(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(sDelete.getText().equals("Add Member")){
            PopUPForm.openFXMLPopupForm("AddmemberForm.fxml",popupStage);
        }
        else {
            boolean delete = memberDao.Delete(sMemberName.getText());

            if (delete){
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
            }
        }
    }

    public void tDeleteonActhion(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(tDelete.getText().equals("Add Member")){
            PopUPForm.openFXMLPopupForm("AddmemberForm.fxml",popupStage);
        }
        else {
            boolean delete = memberDao.Delete(tMemberName.getText());

            if (delete){
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
            }
        }
    }

    public void foDeleteOnActhion(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(foDelete.getText().equals("Add Member")){
            PopUPForm.openFXMLPopupForm("AddmemberForm.fxml",popupStage);
        }
        else {
            boolean delete = memberDao.Delete(foMemberName.getText());

            if (delete){
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstImage.setImage(null);
        secondImage.setImage(null);
        thirdImage.setImage(null);
        fourthImage.setImage(null);
    }
}
