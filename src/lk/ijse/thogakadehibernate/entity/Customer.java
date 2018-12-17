package lk.ijse.thogakadehibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    private String id;

    private String name;
    private String address;
    private int contactNo;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST)
    private List<Orders> orders=new ArrayList<>();

    public Customer() {
    }

    public Customer(String id, String name, String address, int contactNo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo=" + contactNo +
                ", orders=" + orders +
                '}';
    }
}
