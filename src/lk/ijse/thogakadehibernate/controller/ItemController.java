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
import lk.ijse.thogakadehibernate.business.custom.ItemBO;
import lk.ijse.thogakadehibernate.entity.Item;
import lk.ijse.thogakadehibernate.generator.IDGenerator;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public TableView<Item> tblItems;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;

    private ItemBO itemBO=null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);

        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        loadItemTbl();
        loadItemCode();
        setBottonDisableOrNot();
    }

    private void loadItemCode() {
        try {
            String newID = IDGenerator.getNewID("Item", "id", "i");
            txtItemCode.setText(newID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadItemTbl() {
        try {
            tblItems.setItems(FXCollections.observableArrayList(itemBO.getAllItem()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectItemFromTbl(MouseEvent mouseEvent) {
        Item selectedItem = tblItems.getSelectionModel().getSelectedItem();
        txtItemCode.setText(selectedItem.getCode());
        txtDescription.setText(selectedItem.getDescription());
        txtUnitPrice.setText(String.valueOf(selectedItem.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(selectedItem.getQtyOnHand()));

        btnAdd.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void addItem(ActionEvent actionEvent) {
        String itemCodeText = txtItemCode.getText();
        String txtDescriptionText = txtDescription.getText();
        double unitPriceText = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHandText = Integer.parseInt(txtQtyOnHand.getText());
        try {
            boolean isOk = itemBO.addItem(new Item(itemCodeText, txtDescriptionText, unitPriceText, qtyOnHandText));
            if (isOk){
                loadItemTbl();
                clearTextField();
                loadItemCode();
                setBottonDisableOrNot();
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

    public void updateItem(ActionEvent actionEvent) {
        String itemCodeText = txtItemCode.getText();
        String txtDescriptionText = txtDescription.getText();
        double unitPriceText = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHandText = Integer.parseInt(txtQtyOnHand.getText());
        try {
            boolean isOk = itemBO.updateItem(new Item(itemCodeText, txtDescriptionText, unitPriceText, qtyOnHandText));
            if (isOk){
                loadItemTbl();
                clearTextField();
                loadItemCode();
                setBottonDisableOrNot();
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

    public void deleteItem(ActionEvent actionEvent) {
        String itemCodeText = txtItemCode.getText();
        try {
            boolean isOk = itemBO.deleteItem(itemCodeText);
            if (isOk){
                loadItemTbl();
                clearTextField();
                loadItemCode();
                setBottonDisableOrNot();
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

    private void setBottonDisableOrNot(){
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void clearTextField(){
        txtItemCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }
}
