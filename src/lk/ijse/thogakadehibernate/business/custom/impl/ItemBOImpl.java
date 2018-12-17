package lk.ijse.thogakadehibernate.business.custom.impl;

import lk.ijse.thogakadehibernate.business.custom.ItemBO;
import lk.ijse.thogakadehibernate.entity.Item;
import lk.ijse.thogakadehibernate.utill.HibernateUtill;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    @Override
    public boolean addItem(Item item) throws Exception {
        SessionFactory sessionFactory = HibernateUtill.getSessionFactory();
        try(Session session=sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(item);
            session.getTransaction().commit();
        }catch (HibernateException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateItem(Item item) throws Exception {
        SessionFactory sessionFactory = HibernateUtill.getSessionFactory();
        try(Session session=sessionFactory.openSession()) {
            session.beginTransaction();
            Item searchedItem = session.get(Item.class, item.getCode());
            searchedItem.setDescription(item.getDescription());
            searchedItem.setUnitPrice(item.getUnitPrice());
            searchedItem.setQtyOnHand(item.getQtyOnHand());
            session.getTransaction().commit();
        }catch (HibernateException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        SessionFactory sessionFactory = HibernateUtill.getSessionFactory();
        try(Session session=sessionFactory.openSession()) {
            session.beginTransaction();
            Item searchedItem = session.get(Item.class, id);
            session.remove(searchedItem);
            session.getTransaction().commit();
        }catch (HibernateException e) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Item> getAllItem() throws Exception {
        ArrayList<Item> entries=null;
        SessionFactory sessionFactory = HibernateUtill.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query =session.createNativeQuery("SELECT * FROM item");
            query.setResultTransformer(Transformers.aliasToBean(Item.class));
            entries = (ArrayList<Item>) query.getResultList();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            return null;
        }
        return entries;
    }
}
