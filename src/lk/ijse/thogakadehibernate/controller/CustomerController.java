package lk.ijse.thogakadehibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.thogakadehibernate.business.BOFactory;
import lk.ijse.thogakadehibernate.business.custom.CustomerBO;
import lk.ijse.thogakadehibernate.entity.Customer;
import lk.ijse.thogakadehibernate.generator.IDGenerator;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    public TableView<Customer> tblCustomers;
    public JFXTextField txtCustomerID;
    public JFXTextField txtCustomerName;
    public JFXTextField txtContactNo;
    public JFXTextField txtAddress;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;

    private CustomerBO customerBO=null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        loadCustomerTbl();
        loadCustID();
       setButtonDisableOrNot();
    }

    private void loadCustID() {
        try {
            String newID = IDGenerator.getNewID("Customer", "id", "c");
            txtCustomerID.setText(newID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerTbl() {
        try {
            tblCustomers.setItems(FXCollections.observableArrayList(customerBO.getAllCustomer()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectCustomersFromTbl(MouseEvent mouseEvent) {
        Customer selectedItem = tblCustomers.getSelectionModel().getSelectedItem();
        txtCustomerID.setText(selectedItem.getId());
        txtCustomerName.setText(selectedItem.getName());
        txtAddress.setText(selectedItem.getAddress());
        txtContactNo.setText(String.valueOf(selectedItem.getContactNo()));

        btnAdd.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void addCustomer(ActionEvent actionEvent) {
        String customerIDText = txtCustomerID.getText();
        String customerNameText = txtCustomerName.getText();
        String addressText = txtAddress.getText();
        int contactNoText = Integer.parseInt(txtContactNo.getText());
        try {
            boolean isOk = customerBO.addCustomer(new Customer(customerIDText,customerNameText,addressText,contactNoText));
            if (isOk){
                loadCustomerTbl();
                clearTextField();
                loadCustID();
                setButtonDisableOrNot();
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Successfully", ButtonType.OK);
                a.show();
            }else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK);
                a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(ActionEvent actionEvent) {
        String customerIDText = txtCustomerID.getText();
        String customerNameText = txtCustomerName.getText();
        String addressText = txtAddress.getText();
        int contactNoText = Integer.parseInt(txtContactNo.getText());
        try {
            boolean isOk = customerBO.updateCustomer(new Customer(customerIDText,customerNameText,addressText,contactNoText));
            if (isOk){
                loadCustomerTbl();
                clearTextField();
                loadCustID();
                setButtonDisableOrNot();
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Successfully", ButtonType.OK);
                a.show();
            }else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK);
                a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(ActionEvent actionEvent) {
        String customerIDText = txtCustomerID.getText();
        try {
            boolean isOk = customerBO.deleteCustomer(customerIDText);
            if (isOk){
                loadCustomerTbl();
                clearTextField();
                loadCustID();
                setButtonDisableOrNot();
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Successfully", ButtonType.OK);
                a.show();
            }else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK);
                a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearTextField(){
        txtCustomerID.clear();
        txtCustomerName.clear();
        txtAddress.clear();
        txtContactNo.clear();
    }

    private void setButtonDisableOrNot(){
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }
}
