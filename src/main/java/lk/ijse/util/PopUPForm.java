package lk.ijse.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopUPForm {

    public static Stage stage;
    public static void openFXMLPopupForm(String FXAL,Stage popupStage) {
        try {
            // Load the FXML file
            stage = popupStage;
            FXMLLoader loader = new FXMLLoader(PopUPForm.class.getResource("/View/"+FXAL));
            Parent root = loader.load();

            // Create a new stage for the pop-up form
            popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            // Set the FXML content in the pop-up stage
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("FXML Popup Form");
            popupStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
