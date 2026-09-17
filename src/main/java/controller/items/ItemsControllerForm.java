package controller.items;

import Service.Custom.ItemService;
import Service.Custom.impl.ItemServiceImpl;
import Service.ServiceFactory;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TM.ItemTM;
import model.entity.Item;
import utill.ServiceType;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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


    ItemService itemService= ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);

    @FXML
    void btnAddItemOnAction(ActionEvent event) {


        Item  item = new Item();
        item.setId(txtID.getText());
        item.setDescription(txtDescription.getSelectionModel().getSelectedItem().toString());
        item.setPacksize(txtPackSize.getText());
        item.setUnitprice(Double.parseDouble(txtUnitPrice.getText()));
        item.setQuantity(Integer.parseInt(txtQty.getText()));


        if(itemService.addItem(item)){
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
       // ItemServiceImpl itemService = new ItemServiceImpl();
        if(itemService.deleteItem(txtID.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Item deleted successfully");
            alert.show();
            loadtable();
        }else{
            Alert alert= new Alert(Alert.AlertType.INFORMATION,"Item deleted unsuccessfully");
            alert.show();

        }




    }

    @FXML
    void btnItemRelodOnAction(ActionEvent event) {

        loadtable();

    }


    @FXML
    void btnSearchItemOnAction(ActionEvent event) {
        //ItemServiceImpl itemService = new ItemServiceImpl();

        Item item = itemService.searchItemById(txtID.getText());

        txtID.setText(item.getId());
        txtPackSize.setText(item.getPacksize());
        txtDescription.setValue(item.getDescription());
        txtQty.setText(String.valueOf(item.getQuantity()));
        txtUnitPrice.setText(String.valueOf(item.getUnitprice()));




    }


    private void loadtable() {

        List<Item> allItems = itemService.getAllItems();
        tblItem.setItems(FXCollections.observableArrayList(allItems));


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
            //System.out.println(newValue);
            setTexttoValues((Item) newValue);
        });
    }

    private void setTexttoValues(Item itemTM) {
        txtID.setText(itemTM.getId());
        txtPackSize.setText(itemTM.getPacksize());
        txtDescription.setValue(itemTM.getDescription());
        txtQty.setText(String.valueOf(itemTM.getQuantity()));
        txtUnitPrice.setText(String.valueOf(itemTM.getUnitprice()));

    }
}
