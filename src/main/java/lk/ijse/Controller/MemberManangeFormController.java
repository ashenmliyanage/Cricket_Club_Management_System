package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.BO.MemberBo;
import lk.ijse.BO.impl.MemberBoImpl;
import lk.ijse.Model.MemberDto;
import lk.ijse.TM.LoadMemberFormController;
import lk.ijse.dao.MemberDao;
import lk.ijse.dao.impl.MemberDaoImpl;
import lk.ijse.util.PopUPForm;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MemberManangeFormController {
    public TextField search;
    public Label Count;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBox;

    MemberBo memberDao = new MemberBoImpl();

    public void initialize() throws IOException, SQLException, ClassNotFoundException {
        IDSAutoFill = TextFields.bindAutoCompletion(search,IdsSuggest);
        Font.loadFont(getClass().getResourceAsStream("/front/AnonymousPro-Bold.ttf"),14);
        Font.loadFont(getClass().getResourceAsStream("/front/Roboto-Regular.ttf"),14);
        Font.loadFont(getClass().getResourceAsStream("/front/Roboto-Bold.ttf"),14);
        LoadTable();
        Count.setText(String.valueOf(memberDao.getMemberCount()));
    }
    public void LoadTable() throws IOException, SQLException, ClassNotFoundException {

        ArrayList<MemberDto> list = memberDao.getAll();

        VBox vBox = new VBox();
        vBox.setSpacing(30);
        int arraySize = list.size() / 4;

        if (list.size() % 4 != 0) {
            arraySize++;
        }

        Node[] node = new Node[arraySize];
        System.out.println(arraySize);

        int count= -1;

        for (int i = 0; i < arraySize; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/LoadMember.fxml"));
            // Load the FXML file and set the controller using FXMLLoader
            Node loadedNode = fxmlLoader.load();
            LoadMemberFormController load = fxmlLoader.getController();
            VBox.setMargin(loadedNode, new Insets(5, 0, 0, 0));
            vBox.getChildren().add(loadedNode);

            ArrayList<MemberDto> sendData = new ArrayList<>();
            for (int j = (count+1); j < (count+5); j++) {
                if (j < list.size()){
                    sendData.add(list.get(j));
                    System.out.println(j);
                }
                else {
                    break;
                }
            }

            count = count+4;
            load.setData(sendData);
        }
        // Set the background color to fully transparent
        vBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

        // Set the background color of the ScrollPane to fully transparent
        scrollPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

        this.vBox.getChildren().clear();
        this.vBox.setStyle("-fx-background-color: transparent;");
        this.vBox.getChildren().add(vBox);
    }

    public static Stage popupStage;
    private void openFXMLPopupForm() {
        PopUPForm.openFXMLPopupForm("AddmemberForm.fxml",popupStage);
    }

    public void addbtnOnActhion(ActionEvent actionEvent) {
        openFXMLPopupForm();
    }

    private AutoCompletionBinding<String> IDSAutoFill;
    private ArrayList<String> IDS;

    {
        try {
            IDS = memberDao.getAllName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private Set<String> IdsSuggest = new HashSet<>(IDS);


    public void searchOnActhion(ActionEvent actionEvent) {
        try {
            MemberDto data = memberDao.getData(search.getText());
            if (data != null){
                FXMLLoader loader = new FXMLLoader(PopUPForm.class.getResource("/View/UpdatememberForm.fxml"));
                Parent root = loader.load();

// Access the controller
                UpdatememberFormController controller = loader.getController();

// Pass data to the controller
                controller.initData(search.getText());

// Create a new stage for the pop-up form
                popupStage = new Stage();
                popupStage.initModality(Modality.APPLICATION_MODAL);
// Set the FXML content in the pop-up stage
                popupStage.setScene(new Scene(root));
                popupStage.setTitle("FXML Popup Form");
                popupStage.showAndWait();
            }
            else {
                new Alert(Alert.AlertType.INFORMATION,"Can't find member").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}