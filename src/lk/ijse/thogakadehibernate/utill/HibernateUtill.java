package lk.ijse.thogakadehibernate.utill;

import lk.ijse.thogakadehibernate.entity.Customer;
import lk.ijse.thogakadehibernate.entity.Item;
import lk.ijse.thogakadehibernate.entity.OrderDetail;
import lk.ijse.thogakadehibernate.entity.Orders;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtill {
    private static SessionFactory sessionFactory=builSessionFactory();
    private static SessionFactory builSessionFactory(){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .loadProperties("hibernate.properties")
                .build();
        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Orders.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(OrderDetail.class)
                .buildMetadata();
        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
