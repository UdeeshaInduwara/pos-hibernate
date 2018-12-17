package lk.ijse.thogakadehibernate.business.custom;

import lk.ijse.thogakadehibernate.business.SuperBO;
import lk.ijse.thogakadehibernate.model.PlaceOrderDTO;

public interface PlaceOrderBO extends SuperBO {
    public boolean addOrder(PlaceOrderDTO dto) throws Exception;
}
