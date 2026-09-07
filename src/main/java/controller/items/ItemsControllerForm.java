package controller.items;

import Service.Custom.impl.ItemServiceImpl;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TM.ItemTM;
import model.entity.Item;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ItemsControllerForm implements Initializable {

    @FXML
    private TableColumn tblColD;

    @FXML
    private TableColumn tblColDescription;

    @FXML
    private TableColumn tblColPackSize;

    @FXML
    private TableColumn tblColUnitPrice;

    @FXML
    private TableView tblItem;

    @FXML
    private TableColumn tblcolQty;

    @FXML
    private ComboBox txtDescription;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtPackSize;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnAddItemOnAction(ActionEvent event) {


        Item  item = new Item();
        item.setId(txtID.getText());
        item.setDescription(txtDescription.getSelectionModel().getSelectedItem().toString());
        item.setPacksize(txtPackSize.getText());
        item.setUnitprice(Double.parseDouble(txtUnitPrice.getText()));
        item.setQuantity(Integer.parseInt(txtQty.getText()));


        if(new ItemServiceImpl().addItem(item)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Items added successfully");
            alert.show();
            loadtable();

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Item added unsuccessfully");
            alert.show();
        }


    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {
        //that function write later

    }

    @FXML
    void btnItemRelodOnAction(ActionEvent event) {

        loadtable();

    }


    @FXML
    void btnSearchItemOnAction(ActionEvent event) {
        //that function add later

    }


    private void loadtable() {
        ArrayList<ItemTM> itemTMArrayList = new ArrayList<>() ;





        Connection connection = null;
        try {
            System.out.println(DBConnection.getInstance().getConnection());

            ResultSet rs= DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM Item");

            while (rs.next()) {
                itemTMArrayList.add(new ItemTM(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5)
                ));
                tblItem.setItems(FXCollections.observableArrayList(itemTMArrayList));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtDescription.setItems(FXCollections.observableArrayList(Arrays.asList(
                "Keerisamba Retail",
                "Keerisamba 5Kg",
                "Keerisamba 10Kg",
                "Keerisamba 50Kg",
                "Red Raw Rice",
                "Red Raw Rice 10Kg Pack",
                "Red Raw Rice 50Kg Pack",
                "White Raw Rice 5Kg Pack",
                "White Raw Rice 50Kg Pack",
                "Wattana Dhal 500g",
                "Wattana Dhal 1Kg",
                "Mysoor Dhal 500g",
                "Mysoor Dhal 1Kg",
                "Orient Green Gram 500g",
                "Orient Green Gram 1Kg",
                "Anchor F/C Milk powder 450g",
                "Anchor F/C Milk powder 1Kg",
                "Anchor N/F Milk powder 1Kg",
                "Milo Packet 400g",
                "Lipton Ceylon Tea 100g",
                "Lipton Ceylon Tea 200g",
                "Lipton Ceylon Tea 400g",
                "White Suger 500g",
                "White Suger 1Kg",
                "Astra Margarine 250g",
                "Astra Margarine 500g",
                "Rice Noodle 200g",
                "Rice Noodle 500g",
                "Red Rice Noodle 500g",
                "Coka Cola 1.5L",
                "Coka Cola 500ml",
                "Pepsi 500ml",
                "Pepsi 1.5L",
                "Sprite 500ml",
                "Sprite 1.5L",
                "Tomato Sauce Bottle",
                "Chillie Sauce Bottle",
                "Raw Chillie 100g",
                "Raw Chillie 250g",
                "Raw Chillie 500g",
                "Goraka 100g",
                "Sinnamon Stick 100g",
                "Anchor 400g"
        )));
        loadtable();


        tblColD.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblColPackSize.setCellValueFactory(new PropertyValueFactory<>("packsize"));
        tblColUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        tblcolQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            setTexttoValues((ItemTM) newValue);
        });
    }

    private void setTexttoValues(ItemTM itemTM) {
        txtID.setText(itemTM.getId());
        txtPackSize.setText(itemTM.getPacksize());
        txtDescription.setValue(itemTM.getDescription());
        txtQty.setText(String.valueOf(itemTM.getQuantity()));
        txtUnitPrice.setText(String.valueOf(itemTM.getUnitprice()));

    }
}
