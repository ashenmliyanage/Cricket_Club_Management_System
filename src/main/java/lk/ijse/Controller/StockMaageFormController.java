package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import lk.ijse.BO.MemberBo;
import lk.ijse.BO.StockManageBo;
import lk.ijse.BO.impl.MemberBoImpl;
import lk.ijse.BO.impl.StockManageBoImpl;
import lk.ijse.Model.MemberDto;
import lk.ijse.Model.StockDto;
import lk.ijse.TM.StockTM;
import lk.ijse.util.Validation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StockMaageFormController {
    public TextField search;
    @FXML
    private TextField Count;

    @FXML
    private TableColumn<?, ?> CountCol;

    @FXML
    private DatePicker Date;

    @FXML
    private TextField DomainName;

    @FXML
    private TableView<StockTM> Table;

    @FXML
    private TableColumn<?, ?> TypeCol;

    @FXML
    private ComboBox<String> cmdDomain;

    @FXML
    private ComboBox<String> cmdMemeber;

    @FXML
    private ComboBox<String> cmdType;

    @FXML
    private Label itemCode;

    @FXML
    private TableColumn<?, ?> itemCol;


    @FXML
    private JFXButton Savebtn;


    StockManageBo stockManageDao = new StockManageBoImpl();
    public void initialize() throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/front/"), 14);
        Table.setStyle("-fx-background-color: rgba(147, 236, 249, 0.25);");
        itemCol.setStyle("-fx-background-color: rgba(147, 236, 249, 0.25);");
        CountCol.setStyle("-fx-background-color: rgba(147, 236, 249, 0.25);");
        TypeCol.setStyle("-fx-background-color: rgba(147, 236, 249, 0.25);");

        generateId();
        comboValues();
        loadTable();
        update();
    }

    @FXML
    void savebtnOnActhion(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String code = itemCode.getText();
        String type = cmdType.getValue();
        String date = String.valueOf(Date.getValue());
        String Domin = cmdDomain.getValue();
        String DominName = DomainName.getText();
        String memberId = cmdMemeber.getValue();
        int count = Integer.parseInt(Count.getText());

        if (Savebtn.getText().equals("Save")) {
            if (DomainName.getText().equals("")){
                DominName = "Kamal";
            }
            boolean valid = Validation.MemberValid(date, DominName, count);
            try {
                if (valid) {
                    DominName = "Not Domain";
                    boolean isSaved = stockManageDao.Save(new StockDto(code, type, date, Domin, DominName, memberId, count));
                    clearData();
                    initialize();

                    if (isSaved) {
                        new Alert(Alert.AlertType.INFORMATION, "Save Successful").show();
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            boolean valid = Validation.MemberValid(date, DominName, count);
            if (valid){
                boolean isUpdate = stockManageDao.update(new StockDto(code, type, date, Domin, DominName, memberId, count));
                comboValues();
                initialize();

                if (isUpdate){
                    new Alert(Alert.AlertType.INFORMATION,"Update is successful").show();
                }
            }
        }
    }

    private void clearData() {
        try {
            itemCode.setText(null);
            cmdType.setValue(null);
            Date.setValue(null);
            cmdDomain.setValue(null);
            DomainName.setText(null);
            cmdMemeber.setValue(null);
            Count.clear();
        } catch (Exception e) {

        }
    }

    void generateId() {
        try {
            itemCode.setText(
                    stockManageDao.generateId("code", "stock", "S0")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void comboValues() {
        DomainName.setEditable(false);
        ObservableList<String> items = FXCollections.observableArrayList(
                "Bat",
                "Ball",
                "Wicket Stamp",
                "Helmet",
                "Pad",
                "Gloves"
        );
        cmdType.setItems(items);

        items = FXCollections.observableArrayList(
                "Yes",
                "No"
        );

        cmdDomain.setItems(items);

        items = FXCollections.observableArrayList();


        try {
            ArrayList<MemberDto> list = stockManageDao.geMembertAll();
            System.out.println(list);
            items = FXCollections.observableArrayList();
            items.add("Not a Member");

            for (int i = 0; i < list.size(); i++) {
                items.add(list.get(i).getMember_id());
            }

            cmdMemeber.setItems(items);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void domainonActhion(ActionEvent event) {
        try {
            if (cmdDomain.getValue().equals("Yes")) {
                DomainName.setEditable(true);
            }
        } catch (Exception e) {

        }
    }

    void loadTable() {
        Table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        Table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        Table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("count"));

        Table.getItems().clear();

        /*Get all customers*/
        try {
            ArrayList<StockDto> arrayList = stockManageDao.getAll();

            for (StockDto dto : arrayList) {
                Table.getItems().add(
                        new StockTM(
                                dto.getCode(),
                                dto.getType(),
                                dto.getCount()));
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    void update(){
        Table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Savebtn.setText(newValue != null ? "Update" : "Save");
            Savebtn.setDisable(newValue == null);

            if (newValue != null) {
                itemCode.setText(newValue.getItemCode());
                try {
                    StockDto data = stockManageDao.getData(newValue.getItemCode());

                    itemCode.setText(data.getCode());
                    cmdDomain.setValue(data.getDomain());
                    DomainName.setText(data.getDomain_Name());
                    cmdType.setValue(data.getType());
                    Date.setValue(LocalDate.parse(data.getDate()));
                    cmdMemeber.setValue(data.getMember());
                    Count.setText(String.valueOf(data.getCount()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void searchOnActhion(ActionEvent actionEvent) {


        try {
            StockDto data = stockManageDao.getData(search.getText());

            itemCode.setText(data.getCode());
            cmdDomain.setValue(data.getDomain());
            DomainName.setText(data.getDomain_Name());
            cmdType.setValue(data.getType());
            Date.setValue(LocalDate.parse(data.getDate()));
            cmdMemeber.setValue(data.getMember());
            Count.setText(String.valueOf(data.getCount()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
