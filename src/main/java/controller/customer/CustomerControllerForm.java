package controller.customer;

import Service.Custom.impl.CustomerServiceImpl;
import Service.ServiceFactory;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TM.CustomerTM;
import model.TM.ItemTM;
import model.entity.Customer;
import utill.ServiceType;
import Service.Custom.CustomerService;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerControllerForm implements Initializable {

    @FXML
    private TableView tblCustomer;

    @FXML
    private TableColumn tblcolAddress;

    @FXML
    private TableColumn tblcolCity;

    @FXML
    private TableColumn tblcolDate;

    @FXML
    private TableColumn tblcolId;

    @FXML
    private TableColumn tblcolName;

    @FXML
    private TableColumn tblcolSalary;

    @FXML
    private TableColumn tblcolpostalcode;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPostalcode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;

    @FXML
    private ComboBox txtTitle;

    @FXML
    private TextField txtname;

    CustomerService customerService =ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);

    @FXML
    void btnAddCutomerOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtname.getText();
        String title = txtTitle.getSelectionModel().getSelectedItem().toString();
        double salary = Double.parseDouble(txtSalary.getText());
        String postalcode = txtPostalcode.getText();
        String province = txtProvince.getText();
        String city = txtCity.getText();
        String address = txtAddress.getText();
        Date value = Date.valueOf(txtDate.getValue());



        /*System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Title: " + title);
        System.out.println("Salary: " + salary);
        System.out.println("Postal Code: " + postalcode);
        System.out.println("Province: " + province);
        System.out.println("City: " + city);
        System.out.println("Address: " + address);
        System.out.println("Date: " + value);*/

        Customer customer = new Customer(id, title, name, value, salary, address, city, province, postalcode);
        //System.out.println("Customer: " + customer);

        if(customerService.addCustomer(customer)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Customer Added Successfully");
            alert.show();
            loadtable();
            ClearTextToValues();

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Customer Added Unsuccessfully");
            alert.show();
            loadtable();
            ClearTextToValues();
        }


    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {
        if(customerService.deleteCustomer(txtId.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Customer Deleted Successfully");
            alert.show();
            loadtable();
            ClearTextToValues();
        }else{
            Alert alert =new Alert(Alert.AlertType.INFORMATION,"Customer Deleted Unsuccessfully");
            alert.show();
            ClearTextToValues();
        }


    }

    @FXML
    void btnReloadCustomerOnAction(ActionEvent event) {
        loadtable();

    }



    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {

        Customer customer = customerService.SearchCustomerById(txtId.getText());

        txtname.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtCity.setText(customer.getCity());
        txtPostalcode.setText(customer.getPostalcode());
        txtProvince.setText(customer.getProvince());
        txtTitle.setValue(customer.getTitle());
        txtDate.setValue(customer.getDob().toLocalDate());
        txtSalary.setText(String.valueOf(customer.getSalary()));

    }


    private void loadtable() {



        ClearTextToValues();
        //CustomerServiceImpl customerService = new CustomerServiceImpl();

        List<Customer> allCustomers = customerService.getAllCustomers();

        List<CustomerTM> listCustomerTM = new ArrayList<>();

        allCustomers.forEach(customer -> {
            listCustomerTM.add(new CustomerTM(
                    customer.getId(),
                    customer.getTitle(),
                    customer.getName(),
                    customer.getDob(),
                    customer.getSalary(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalcode()

            ));

        });

        tblCustomer.setItems(FXCollections.observableList(listCustomerTM));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtTitle.getItems().addAll(
                "MR.",
                "MISS."
        );
        tblcolId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcolName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblcolAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblcolCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tblcolDate.setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblcolSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        loadtable();
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           // System.out.println(newValue);
            setTexttoValues((CustomerTM) newValue);

        });

        

    }

    private void setTexttoValues(CustomerTM newValue) {

        txtId.setText(newValue.getId());
        txtProvince.setText(newValue.getProvince());
        txtCity.setText(newValue.getCity());
        txtPostalcode.setText(newValue.getPostalcode());
        txtAddress.setText(newValue.getAddress());
        txtTitle.setValue(newValue.getTitle());
        txtname.setText(newValue.getName());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
        txtDate.setValue(newValue.getDob().toLocalDate());



    }

    private void ClearTextToValues(){
        txtId.setText("");
        txtProvince.setText("");
        txtCity.setText("");
        txtPostalcode.setText("");
        txtAddress.setText("");
        txtTitle.setValue("");
        txtname.setText("");
        txtSalary.setText("");
        txtDate.setValue(null);
    }


}
