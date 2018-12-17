package lk.ijse.thogakadehibernate.model;

public class SelectedItemDTO {
    private String code;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
    private int choosedQty;
    private double total;

    public SelectedItemDTO() {
    }

    public SelectedItemDTO(String code, String description, double unitPrice, int qtyOnHand, int choosedQty, double total) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        this.choosedQty = choosedQty;
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public int getChoosedQty() {
        return choosedQty;
    }

    public void setChoosedQty(int choosedQty) {
        this.choosedQty = choosedQty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SelectedItemDTO{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                ", choosedQty=" + choosedQty +
                ", total=" + total +
                '}';
    }
}
