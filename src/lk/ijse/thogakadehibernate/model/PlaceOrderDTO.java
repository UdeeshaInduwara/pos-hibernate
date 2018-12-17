package lk.ijse.thogakadehibernate.model;

import java.util.ArrayList;

public class PlaceOrderDTO {
    private String oid;
    private String custId;
    private ArrayList<SelectedItemDTO> selectedItem=new ArrayList<>();

    public PlaceOrderDTO() {
    }

    public PlaceOrderDTO(String oid, String custId, ArrayList<SelectedItemDTO> selectedItem) {
        this.oid = oid;
        this.custId = custId;
        this.selectedItem = selectedItem;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public ArrayList<SelectedItemDTO> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(ArrayList<SelectedItemDTO> selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Override
    public String toString() {
        return "PlaceOrderDTO{" +
                "oid='" + oid + '\'' +
                ", custId='" + custId + '\'' +
                ", selectedItem=" + selectedItem +
                '}';
    }
}
