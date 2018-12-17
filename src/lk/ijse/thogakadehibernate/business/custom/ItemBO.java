package lk.ijse.thogakadehibernate.business.custom;

import lk.ijse.thogakadehibernate.business.SuperBO;
import lk.ijse.thogakadehibernate.entity.Item;

import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public boolean addItem(Item item) throws Exception;
    public boolean updateItem(Item item) throws Exception;
    public boolean deleteItem(String id) throws Exception;
    public ArrayList<Item> getAllItem() throws Exception;
}
