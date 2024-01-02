package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.BO.MemberBo;
import lk.ijse.BO.impl.MemberBoImpl;
import lk.ijse.Model.MemberDto;
import lk.ijse.TM.LoadMemberFormController;
import lk.ijse.dao.MemberDao;
import lk.ijse.dao.impl.MemberDaoImpl;
import lk.ijse.util.ImageAutoCrop;
import lk.ijse.util.PopUPForm;
import lk.ijse.util.Validation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdatememberFormController {

    public Label label;
    @FXML
    private TextField Address;

    @FXML
    private TextField Age;

    @FXML
    private DatePicker Bod;

    @FXML
    private TextField Email;

    @FXML
    private TextField FullName;

    @FXML
    private ComboBox<String> Position;

    @FXML
    private ImageView img;

    MemberBo memberDao = new MemberBoImpl();
    public void initialize() throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/front/"),14);
        ObservableList<String> observableArray = FXCollections.observableArrayList(
                "All Rounder",
                "Batsman",
                "Bowler"
        );
        Position.setItems(observableArray);
    }


    public  void initData(String name){
        loadData(name);
    }

    void loadData(String name){
        try {
            MemberDto data = memberDao.getData(name);
            System.out.println(data);

            memberId = data.getMember_id();
            FullName.setText(data.getFull_name());
            label.setText(data.getFull_name());
            Position.setValue(data.getPosition());
            Bod.setValue(LocalDate.parse(data.getBod()));
            Age.setText(String.valueOf(data.getAge()));
            Email.setText(data.getEmail());
            Address.setText(data.getAddress());
            img.setImage(new Image(data.getImage()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    InputStream inputStream;
    String memberId = null;

    public void importbtnOnActhion(ActionEvent actionEvent) {
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
            this.img.setImage(image);
            this.img.setPreserveRatio(true);
            this.img.setFitWidth(275);
            this.img.setFitHeight(275);

            //___________________________________________________________________________

            File file = new File(path);
            try {
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updatebtnOnActhion(ActionEvent actionEvent) {
        String fullNameText = FullName.getText();
        String positionText = Position.getValue();
        String bodText = String.valueOf(Bod.getValue());
        int ageText = Integer.parseInt(Age.getText());
        String emailText = Email.getText();
        String addressText = Address.getText();
        InputStream inputStream1 = imageToInputStream(img.getImage());

        boolean valid = Validation.MemberValid(fullNameText, bodText, ageText, emailText, addressText, inputStream1);

        try {
            if (valid) {
                boolean update = memberDao.update(new MemberDto(
                        memberId, fullNameText, positionText, bodText, ageText, emailText, addressText, inputStream1
                ));

                if (update) {
                    new Alert(Alert.AlertType.INFORMATION, "Updated").show();
                }
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Validate is not Successful").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //------------------------------------------------------------------------------

    public static InputStream imageToInputStream(Image image) {
        try {
            // Convert JavaFX Image to BufferedImage
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

            // Convert BufferedImage to InputStream
            return toInputStream(bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception according to your needs
            return null;
        }
    }

    private static InputStream toInputStream(BufferedImage bufferedImage) throws IOException {
        // Create a ByteArrayOutputStream to hold the image data
        // Use ImageIO to write the BufferedImage to the ByteArrayOutputStream
        // Convert the ByteArrayOutputStream to a ByteArrayInputStream
        // This ByteArrayInputStream can now be used as an InputStream
        // containing the image data
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

}
