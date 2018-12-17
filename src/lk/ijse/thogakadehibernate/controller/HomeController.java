package lk.ijse.thogakadehibernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public AnchorPane paneRoot;
    public JFXButton btnPlaceOrder;
    public JFXButton btnCustomer;
    public JFXButton btnItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/thogakadehibernate/view/PlaceOrder.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        paneRoot.getChildren().setAll(pane);
        setButtonColor(1);
    }

    public void placeOrder(ActionEvent actionEvent) {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/thogakadehibernate/view/PlaceOrder.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        paneRoot.getChildren().setAll(pane);
        setButtonColor(1);
    }

    public void customers(ActionEvent actionEvent) {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/thogakadehibernate/view/Customer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        paneRoot.getChildren().setAll(pane);
        setButtonColor(2);
    }

    public void items(ActionEvent actionEvent) {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/thogakadehibernate/view/Item.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        paneRoot.getChildren().setAll(pane);
        setButtonColor(3);
    }
    private void setButtonColor(int num) {
        switch (num){
            case 1:{
                btnPlaceOrder.setStyle("-fx-background-color: #0c0d3e");
                btnCustomer.setStyle("-fx-background-color: #C0C0C0");
                btnItem.setStyle("-fx-background-color: #C0C0C0");
            }break;
            case 2:{
                btnCustomer.setStyle("-fx-background-color: #0c0d3e");
                btnPlaceOrder.setStyle("-fx-background-color: #C0C0C0");
                btnItem.setStyle("-fx-background-color: #C0C0C0");
            }break;
            case 3:{
                btnItem.setStyle("-fx-background-color: #0c0d3e");
                btnPlaceOrder.setStyle("-fx-background-color: #C0C0C0");
                btnCustomer.setStyle("-fx-background-color: #C0C0C0");
            }break;
        }
    }
}
