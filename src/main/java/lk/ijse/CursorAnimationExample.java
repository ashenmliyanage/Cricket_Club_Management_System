package lk.ijse;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CursorAnimationExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cursor Animation Example");

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 300);

        Text contentText = new Text("Move your cursor here!");

        // Set the initial cursor
        contentText.setOnMouseEntered(event -> scene.setCursor(javafx.scene.Cursor.HAND));
        contentText.setOnMouseExited(event -> scene.setCursor(javafx.scene.Cursor.DEFAULT));

        root.getChildren().add(contentText);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), contentText);
        translateTransition.setByX(100); // Move the text by 100 pixels horizontally
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE); // Repeat the animation indefinitely
        translateTransition.setAutoReverse(true); // Reverse the animation on each cycle

        primaryStage.setScene(scene);
        primaryStage.show();

        // Start the animation
        translateTransition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
