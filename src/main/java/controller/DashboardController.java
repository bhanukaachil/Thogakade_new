package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashboardController {

    public AnchorPane Dashboard;
    @FXML
    private Label lblTitle;


    @FXML
    void btnCustomerForm(ActionEvent event) {

        try {

            URL resource = this.getClass().getResource("/view/CustomerForm.fxml");
            System.out.println(resource);
            assert  resource != null;

            Parent parent= FXMLLoader.load(resource);
            System.out.println(parent);

            Dashboard.getChildren().clear();
            Dashboard.getChildren().add(parent);
            lblTitle.setText("Customer Form");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnItemsForm(ActionEvent event) {

        try {

            URL resource = this.getClass().getResource("/view/ItemsForm.fxml");
            System.out.println(resource);
            assert  resource != null;

            Parent parent= FXMLLoader.load(resource);
            System.out.println(parent);

            Dashboard.getChildren().clear();
            Dashboard.getChildren().add(parent);
            lblTitle.setText("Items Form");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnOrderForm(ActionEvent event) {

        try {

            URL resource = this.getClass().getResource("/view/OrderForm.fxml");
            System.out.println(resource);
            assert  resource != null;

            Parent parent= FXMLLoader.load(resource);
            System.out.println(parent);

            Dashboard.getChildren().clear();
            Dashboard.getChildren().add(parent);
            lblTitle.setText("Orders Form");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
