package lk.ijse.thogakadehibernate.entity;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue
    private int oid;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Orders orders;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Item item;

    private int quantity;
    private double unitprice;

    public OrderDetail() {
    }

    public OrderDetail(int quantity, double unitprice) {
        this.quantity = quantity;
        this.unitprice = unitprice;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orders=" + orders +
                ", item=" + item +
                ", quantity=" + quantity +
                ", unitprice=" + unitprice +
                '}';
    }
}
