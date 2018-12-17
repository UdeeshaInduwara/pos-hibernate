package lk.ijse.thogakadehibernate.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.thogakadehibernate.business.BOFactory;
import lk.ijse.thogakadehibernate.business.custom.CustomerBO;
import lk.ijse.thogakadehibernate.business.custom.ItemBO;
import lk.ijse.thogakadehibernate.business.custom.PlaceOrderBO;
import lk.ijse.thogakadehibernate.entity.Customer;
import lk.ijse.thogakadehibernate.entity.Item;
import lk.ijse.thogakadehibernate.generator.IDGenerator;
import lk.ijse.thogakadehibernate.model.PlaceOrderDTO;
import lk.ijse.thogakadehibernate.model.SelectedItemDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {
    public TableView<Item> tblSelectItem;
    public TextField txtChoseQty;
    public JFXComboBox<String> cmbCustomerID;
    public JFXTextField txtCustomerName;
    public Label lblOrderID;
    public Label lblOrderDate;
    public TableView<SelectedItemDTO> tblOrderDetail;
    public TextField txtTotal;

    private ItemBO itemBO=null;
    private CustomerBO customerBO=null;
    private PlaceOrderBO placeOrderBO=null;
    private Item selectedItem=null;
    private ArrayList<SelectedItemDTO> selectedItemDTOArrayList=new ArrayList<>();
    private double fullAmount=0;
    private String orderID="";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
        customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        placeOrderBO=BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACEORDER);

        tblSelectItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblSelectItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblSelectItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblSelectItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        loadItemTbl();
        loadOrderId();

        try {
            ArrayList<Customer> allCustomer = customerBO.getAllCustomer();
            ArrayList<String> idList=new ArrayList<>();
            for (Customer customer : allCustomer) {
                idList.add(customer.getId());
            }
            cmbCustomerID.setItems(FXCollections.observableArrayList(idList));
        } catch (Exception e) {
            e.printStackTrace();
        }

        tblOrderDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblOrderDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblOrderDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("choosedQty"));
        tblOrderDetail.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void loadOrderId() {
        try {
            String newID = IDGenerator.getNewID("Orders", "id", "od");
            orderID=newID;
            lblOrderID.setText(newID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadItemTbl() {
        try {
            tblSelectItem.setItems(FXCollections.observableArrayList(itemBO.getAllItem()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectItem(MouseEvent mouseEvent) {
        selectedItem = tblSelectItem.getSelectionModel().getSelectedItem();
    }

    public void addItemsToOrderDetailTbl(ActionEvent actionEvent) {
        int choosedQty = Integer.parseInt(txtChoseQty.getText());
        if (choosedQty>selectedItem.getQtyOnHand()){
            txtChoseQty.selectAll();
            return;
        }
        double selectedItemTotal=selectedItem.getUnitPrice()*choosedQty;
        SelectedItemDTO selItem=new SelectedItemDTO(selectedItem.getCode(),selectedItem.getDescription(),selectedItem.getUnitPrice(),selectedItem.getQtyOnHand(),choosedQty,selectedItemTotal);

        selectedItemDTOArrayList.add(selItem);
        tblOrderDetail.setItems(FXCollections.observableArrayList(selectedItemDTOArrayList));

        fullAmount+=selectedItemTotal;
        txtTotal.setText(String.valueOf(fullAmount));
    }

    public void selectCustomerID(ActionEvent actionEvent) {
        String id = cmbCustomerID.getSelectionModel().getSelectedItem();
        try {
            Customer customer = customerBO.searchCustomer(id);
            txtCustomerName.setText(customer.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectCustomerName(ActionEvent actionEvent) {

    }

    public void deleteSelectedItem(MouseEvent mouseEvent) {
        SelectedItemDTO selectedItem = tblOrderDetail.getSelectionModel().getSelectedItem();
        int i = selectedItemDTOArrayList.indexOf(selectedItem);
        selectedItemDTOArrayList.remove(i);
        tblOrderDetail.setItems(FXCollections.observableArrayList(selectedItemDTOArrayList));

        fullAmount-=selectedItem.getTotal();
        txtTotal.setText(String.valueOf(fullAmount));
    }

    public void saveOrder(ActionEvent actionEvent) {
        String custId = cmbCustomerID.getSelectionModel().getSelectedItem();
        PlaceOrderDTO placeOrderDTO = new PlaceOrderDTO(orderID, custId, selectedItemDTOArrayList);
        try {
            boolean isOk = placeOrderBO.addOrder(placeOrderDTO);
            if (isOk){
                selectedItemDTOArrayList.clear();
                tblOrderDetail.setItems(FXCollections.observableArrayList(selectedItemDTOArrayList));
                loadItemTbl();
                fullAmount=0;
                txtTotal.setText(String.valueOf(fullAmount));
                txtChoseQty.clear();
                loadOrderId();
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

}
