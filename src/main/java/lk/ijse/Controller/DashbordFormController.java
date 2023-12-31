package lk.ijse.Controller;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Model.MemberDto;
import lk.ijse.dao.MemberDao;
import lk.ijse.dao.StockManageDao;
import lk.ijse.dao.impl.MemberDaoImpl;
import lk.ijse.dao.impl.StockManageDaoimpl;
import lombok.SneakyThrows;


import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.net.URL;
import static javafx.application.Application.launch;

public class DashbordFormController implements Initializable {


    //   private static final int SLIDE_DURATION_SECONDS = 2;
    public Label lbldate;
    public Label lbldate2;
    public LineChart Chart;

    private List<Image> images;
    private int currentImageIndex;
    private Timeline timeline;

    @FXML
    private Label Loc1;

    @FXML
    private Label Loc2;

    @FXML
    private Label Loc3;

    @FXML
    private Label Loc4;

    @FXML
    private Label Loc5;

    @FXML
    private Label Loc6;

    @FXML
    private Label Name1;

    @FXML
    private Label Name2;

    @FXML
    private Label Name3;

    @FXML
    private Label Name4;

    @FXML
    private Label Name5;

    @FXML
    private Label Name6;

//    private void initializeImageSlideshow() {
//
//        images = loadImages();
//
//        ImageView imageView = new ImageView();
//        root.getChildren().add(imageView);
//
//        // Set the initial image
//        imageView.setImage(images.get(0));
//
//        timeline = new Timeline(
//                new KeyFrame(Duration.seconds(SLIDE_DURATION_SECONDS), event -> {
//                    currentImageIndex = (currentImageIndex + 1) % images.size();
//                    imageView.setImage(images.get(currentImageIndex));
//                    imageView.setFitHeight(465);
//                    imageView.setFitWidth(698);
//                })
//        );
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//    }
//
//
//    private List<Image> loadImages() {
//        List<Image> images = new ArrayList<>();
//        images.add(new Image(getClass().getResourceAsStream("/Images/1243143692-2048x2048 1.png")));
//        images.add(new Image(getClass().getResourceAsStream("/Images/Firefly 20231212094930.png")));
//        images.add(new Image(getClass().getResourceAsStream("/Images/wp4975372-kumar-sangakkara-wallpapers.jpg")));
//        //images.add(new Image(getClass().getResourceAsStream("/Images/gettyimages-1243143692-2048x2048.jpg")));
//        // Add more images as needed
//        return images;
//    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font.loadFont(getClass().getResourceAsStream("/front/Roboto-Medium.ttf"), 17);
        Font.loadFont(getClass().getResourceAsStream("/front/Roboto-Regular.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/front/Roboto-Bold.ttf"), 14);
        generateRealTime();
        getLastSixMembers();
        getItemCount();

//        root.setMaxWidth(698);
//        root.setMaxHeight(465);
//        ChangeImage.setFitHeight(465);
//        ChangeImage.setFitWidth(698);
//        initializeImageSlideshow();

        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("10", 7));
        series.getData().add(new XYChart.Data("20", 5));
        series.getData().add(new XYChart.Data("30", 9));
        series.getData().add(new XYChart.Data("40", 6));
        series.getData().add(new XYChart.Data("50", 4));
        Chart.getData().add(series);
        Chart.lookup(".chart-plot-background").setStyle("-fx-background-color:rgba(0,0,0,0)");
    }

    private void generateRealTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        lbldate2.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {

            lbldate.setText(timeNow());
            // lblTime.setText(LocalDateTime.now().format(formatter));

        }),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public String timeNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        //System.out.println(dateFormat.format(new Date()));
        return dateFormat.format(new Date());
    }

    @FXML
    private void playMouseEnterAnimation(MouseEvent event) throws SQLException, ClassNotFoundException {
        if (event.getSource() instanceof ImageView) {

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
    }

    @FXML
    private void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
        }
    }

    public void getLastSixMembers() {
        MemberDao dao = new MemberDaoImpl();
        try {
            ArrayList<MemberDto> sixData = dao.getSixData();
            Name1.setText(sixData.get(0).getFull_name());
            Name2.setText(sixData.get(1).getFull_name());
            Name3.setText(sixData.get(2).getFull_name());
            Name4.setText(sixData.get(3).getFull_name());
            Name5.setText(sixData.get(4).getFull_name());
            Name6.setText(sixData.get(5).getFull_name());

            Loc1.setText(sixData.get(0).getAddress());
            Loc2.setText(sixData.get(1).getAddress());
            Loc3.setText(sixData.get(2).getAddress());
            Loc4.setText(sixData.get(3).getAddress());
            Loc5.setText(sixData.get(4).getAddress());
            Loc6.setText(sixData.get(5).getAddress());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Label Ball;

    @FXML
    private Label Bat;

    @FXML
    private Label Gloves;

    @FXML
    private Label Helmet;

    @FXML
    private Label Pad;

    @FXML
    private Label Wicket;

    void getItemCount() throws SQLException, ClassNotFoundException {
        StockManageDao dao = new StockManageDaoimpl();
        int[] itemCounts = dao.getItemCounts();
        Bat.setText(String.valueOf(itemCounts[0]));
        Ball.setText(String.valueOf(itemCounts[1]));
        Wicket.setText(String.valueOf(itemCounts[2]));
        Helmet.setText(String.valueOf(itemCounts[3]));
        Pad.setText(String.valueOf(itemCounts[4]));
        Gloves.setText(String.valueOf(itemCounts[5]));
    }

    private void openWebsite(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void webclick(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            switch (icon.getId()) {
                case "SLCricket":
                    openWebsite("https://srilankacricket.lk/");
                    break;
                case "Icc":
                    openWebsite("https://www.icc-cricket.com/homepage");
                    break;
                case "GalleCricket":
                    openWebsite("https://gallecricketclub.lk/");
                    break;
            }
        }
    }
}
