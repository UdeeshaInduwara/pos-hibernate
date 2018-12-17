package lk.ijse.thogakadehibernate.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.thogakadehibernate.utill.HibernateUtill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main extends Application {
    public static void main(String[] args) {
//        SessionFactory sessionFactory = HibernateUtill.getSessionFactory();
//        try(Session session=sessionFactory.openSession()) {
//            session.beginTransaction();
//
//            session.getTransaction().commit();
//        }
//        sessionFactory.close();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/thogakadehibernate/view/Home.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Thogakade Hibernate");
        primaryStage.show();
    }
}
