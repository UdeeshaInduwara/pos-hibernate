package lk.ijse.thogakadehibernate.business.custom;

import lk.ijse.thogakadehibernate.business.SuperBO;
import lk.ijse.thogakadehibernate.entity.Customer;

import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public boolean addCustomer(Customer customer) throws Exception;
    public boolean updateCustomer(Customer customer) throws Exception;
    public boolean deleteCustomer(String id) throws Exception;
    public Customer searchCustomer(String id) throws Exception;
    public ArrayList<Customer> getAllCustomer() throws Exception;
}
