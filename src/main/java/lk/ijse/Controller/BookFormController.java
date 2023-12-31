package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.Model.BookDto;
import lk.ijse.Model.MemberDto;
import lk.ijse.Model.StockDto;
import lk.ijse.TM.bookTm;
import lk.ijse.dao.BookDao;
import lk.ijse.dao.MemberDao;
import lk.ijse.dao.StockManageDao;
import lk.ijse.dao.impl.BookDaoimpl;
import lk.ijse.dao.impl.MemberDaoImpl;
import lk.ijse.dao.impl.StockManageDaoimpl;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookFormController implements Initializable {

    public JFXButton getItem;
    public Label QtyOnHand;
    public TextField Count;
    public TableColumn itemCol;
    public TableColumn CountCol;
    public TableColumn DeleteCol;
    public TableColumn ItemCodeCol;

    @FXML
    private ComboBox<String> ItemCmb;

    @FXML
    private TextField MemberName;

    @FXML
    private Label Member_Id;

    @FXML
    private Label Type;

    @FXML
    private TableView<bookTm> table;

    private final ObservableList<bookTm> obList = FXCollections.observableArrayList();
    @FXML
    void AddbtnOnActhion(ActionEvent event) {

        try {
            String ItemCode = this.ItemCmb.getValue();
            String MemberId = Member_Id.getText();
            String MemberName = this.MemberName.getText();
            int qty = Integer.parseInt(Count.getText());
            int gtyOnHand = Integer.parseInt(QtyOnHand.getText());
            Button btn = new Button("remove");
            btn.setCursor(Cursor.HAND);

            if (gtyOnHand > qty) {

                if (!MemberId.equals("Find Id")) {

                    btn.setOnAction((e) -> {
                        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                        if (type.orElse(no) == yes) {
                            obList.remove(table.getSelectionModel().getFocusedIndex());
                            table.refresh();

                            calculateNetTotal();
                        }
                    });

                    obList.add(new bookTm(
                            ItemCode,
                            Type.getText(),
                            Count.getText(),
                            btn
                    ));

                    table.setItems(obList);
                    calculateNetTotal();
                    //qty.clear();
                }
                else {
                    new Alert(Alert.AlertType.INFORMATION,"Can't Find Member").show();
                }
            }
            else {
                new Alert(Alert.AlertType.INFORMATION,"Not much qty").show();
            }
        }catch (NumberFormatException e){
            new Alert(Alert.AlertType.INFORMATION,"Please enter only correct data").show();
        }
        catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Fill All colum"+e.getMessage()).show();
        }



    }


    private void calculateNetTotal() {
        for (int i = 0; i < table.getItems().size(); i++) {
            double d =  Double.parseDouble((String) CountCol.getCellData(i));
            count = count+d;
        }
        //lblNetTotal.setText(String.valueOf(total));
    }

    @FXML
    void MemberNameOnActhion(ActionEvent event) throws SQLException, ClassNotFoundException {
        MemberDao memberDao = new MemberDaoImpl();
        MemberDto data = memberDao.getData(MemberName.getText());

        try {
            Member_Id.setText(data.getMember_id());
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Cant not find Member").show();
        }
    }

    double count = 0;

    @FXML
    void getitemOnActhion(ActionEvent event) {

        String orderId = generateNextOrderId();
        String cusId = Member_Id.getText();
        String date = setDate();
        int qty = Integer.parseInt(this.QtyOnHand.getText());

        List<bookTm> tmList = new ArrayList<>();

        for (bookTm cartTm : obList) {
            tmList.add(cartTm);
        }

        BookDto bookDto = new BookDto(
                orderId,
                cusId,
                date,
                count,
                qty,
                tmList
        );

        try {
            BookDao bookDao = new BookDaoimpl();
            boolean isSuccess = bookDao.Save(bookDto);
            if(isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "order completed!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void itemcbmOnActhion(ActionEvent actionEvent) {
        StockManageDao stockManageDao = new StockManageDaoimpl();
        try {
            StockDto data = stockManageDao.getData(this.ItemCmb.getValue());
            Type.setText(data.getType());
            QtyOnHand.setText(String.valueOf(data.getCount()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void Table(){
        itemCol.setCellValueFactory(new PropertyValueFactory<>("item"));
        CountCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        DeleteCol.setCellValueFactory(new PropertyValueFactory<>("btn"));
        ItemCodeCol.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        loadItemCodes();
    }
    private String generateNextOrderId() {
        BookDao bookDao = new BookDaoimpl();
        try {
            return bookDao.generateId("Book_id","orders","B");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        StockManageDao stockManageDao = new StockManageDaoimpl();
        try {
            List<StockDto> itemList = stockManageDao.getAll();

            for (StockDto itemDto : itemList) {
                obList.add(itemDto.getCode());
            }

            this.ItemCmb.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String setDate() {
        return String.valueOf(LocalDate.now());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Table();
    }


}
