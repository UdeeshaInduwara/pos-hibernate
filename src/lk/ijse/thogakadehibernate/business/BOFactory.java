package lk.ijse.thogakadehibernate.business;

import lk.ijse.thogakadehibernate.business.custom.impl.CustomerBOImpl;
import lk.ijse.thogakadehibernate.business.custom.impl.ItemBOImpl;
import lk.ijse.thogakadehibernate.business.custom.impl.PlaceOrderBOImpl;

public class BOFactory {
    public enum BOTypes{
        ITEM,CUSTOMER,PLACEORDER
    }
    private static BOFactory boFactory;
    public static BOFactory getInstance(){
        if(boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }
    private BOFactory(){
    }
    public <T extends SuperBO> T getBO(BOTypes boType){
        switch(boType){
            case ITEM: return (T) new ItemBOImpl();
            case CUSTOMER: return (T) new CustomerBOImpl();
            case PLACEORDER: return (T) new PlaceOrderBOImpl();
            default: return null;
        }
    }
}
