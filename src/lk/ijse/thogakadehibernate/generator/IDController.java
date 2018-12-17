package lk.ijse.thogakadehibernate.generator;


import lk.ijse.thogakadehibernate.entity.Customer;
import lk.ijse.thogakadehibernate.entity.Item;
import lk.ijse.thogakadehibernate.entity.Orders;
import lk.ijse.thogakadehibernate.utill.HibernateUtill;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class IDController {
    public static String getLastID(String tableName,String columnName) throws Exception{
        Object table=null;
        String returnId=null;
        SessionFactory sessionFactory = HibernateUtill.getSessionFactory();
        try(Session session=sessionFactory.openSession()) {
            session.beginTransaction();

            table = session.createQuery("from "+tableName+" ORDER BY "+columnName+" DESC")
                    .setMaxResults(1).uniqueResult();

            session.getTransaction().commit();
        }
        switch (tableName){
            case "Customer":{
                Customer customer=(Customer) table;
                returnId=customer.getId();
            }break;
            case "Item":{
                Item item=(Item) table;
                returnId=item.getCode();
            }break;
            case "Orders":{
                Orders orders=(Orders) table;
                returnId=orders.getId();
            }break;
        }
        return returnId;
    }
}
