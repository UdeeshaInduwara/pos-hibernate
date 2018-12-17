package lk.ijse.thogakadehibernate.business.custom.impl;

import lk.ijse.thogakadehibernate.business.custom.CustomerBO;
import lk.ijse.thogakadehibernate.entity.Customer;
import lk.ijse.thogakadehibernate.utill.HibernateUtill;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    @Override
    public boolean addCustomer(Customer customer) throws Exception {
        SessionFactory sessionFactory = HibernateUtill.getSessionFactory();
        try(Session session=sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(customer);
            session.getTransaction().commit();
        }catch (HibernateException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws Exception {
        SessionFactory sessionFactory = HibernateUtill.getSessionFactory();
        try(Session session=sessionFactory.openSession()) {
            session.beginTransaction();
            Customer searchedCustomer = session.get(Customer.class, customer.getId());
            searchedCustomer.setName(customer.getName());
            searchedCustomer.setAddress(customer.getAddress());
            searchedCustomer.setContactNo(customer.getContactNo());
            session.getTransaction().commit();
        }catch (HibernateException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        SessionFactory sessionFactory = HibernateUtill.getSessionFactory();
        try(Session session=sessionFactory.openSession()) {
            session.beginTransaction();
            Customer searchedCustomer = session.get(Customer.class, id);
            session.remove(searchedCustomer);
            session.getTransaction().commit();
        }catch (HibernateException e) {
            return false;
        }
        return true;
    }

    @Override
    public Customer searchCustomer(String id) throws Exception {
        Customer customer=null;
        SessionFactory sessionFactory = HibernateUtill.getSessionFactory();
        try(Session session=sessionFactory.openSession()) {
            session.beginTransaction();
            customer = session.get(Customer.class, id);
            session.getTransaction().commit();
        }catch (HibernateException e) {
            return customer;
        }
        return customer;
    }


    @Override
    public ArrayList<Customer> getAllCustomer() throws Exception {
        ArrayList<Customer> entries=null;
        SessionFactory sessionFactory = HibernateUtill.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query =session.createNativeQuery("SELECT * FROM customer");
            query.setResultTransformer(Transformers.aliasToBean(Customer.class));
            entries = (ArrayList<Customer>) query.getResultList();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            return null;
        }
        return entries;
    }
}
