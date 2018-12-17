package lk.ijse.thogakadehibernate.business.custom.impl;

import lk.ijse.thogakadehibernate.business.custom.PlaceOrderBO;
import lk.ijse.thogakadehibernate.entity.Customer;
import lk.ijse.thogakadehibernate.entity.Item;
import lk.ijse.thogakadehibernate.entity.OrderDetail;
import lk.ijse.thogakadehibernate.entity.Orders;
import lk.ijse.thogakadehibernate.model.PlaceOrderDTO;
import lk.ijse.thogakadehibernate.model.SelectedItemDTO;
import lk.ijse.thogakadehibernate.utill.HibernateUtill;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Date;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    @Override
    public boolean addOrder(PlaceOrderDTO dto) throws Exception {
        Orders orders = new Orders(dto.getOid(),new Date());
        String custId=dto.getCustId();

        SessionFactory sessionFactory = HibernateUtill.getSessionFactory();
        try(Session session=sessionFactory.openSession()) {
            session.beginTransaction();

            Customer customer = session.get(Customer.class, custId);
            ArrayList<Orders> ordersList=new ArrayList<>();

            orders.setCustomer(customer);
            ordersList.add(orders);

            customer.setOrders(ordersList);

            for (SelectedItemDTO selectedItemDTO: dto.getSelectedItem()) {
                Item searchedItem = session.get(Item.class, selectedItemDTO.getCode());
                ArrayList<OrderDetail> orderDetailList=new ArrayList<>();
                OrderDetail orderDetail = new OrderDetail(selectedItemDTO.getChoosedQty(), selectedItemDTO.getUnitPrice());
                orderDetail.setItem(searchedItem);
                orderDetail.setOrders(orders);

                orderDetailList.add(orderDetail);
                searchedItem.setOrderDetails(orderDetailList);
                orders.setOrderDetails(orderDetailList);

                int qtyOnHand = searchedItem.getQtyOnHand();
                int newQtyOnHand=qtyOnHand-selectedItemDTO.getChoosedQty();
                searchedItem.setQtyOnHand(newQtyOnHand);

                session.persist(searchedItem);
            }

            session.persist(customer);
            session.getTransaction().commit();
        }catch (HibernateException e) {
            return false;
        }
        return true;
    }
}
