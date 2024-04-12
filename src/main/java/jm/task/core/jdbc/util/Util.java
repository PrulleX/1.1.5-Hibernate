package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3307/firstbd";
    private static final String USERNAME = "root2";
    private static final String PASSWORD = "rereirf202020";

    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
           connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("БД подключилась");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("БД не подключилась");
        }
        return connection;
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = getConfiguration();
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                System.out.println("Возникла проблема с созданием фабрики");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();

        settings.put(Environment.DRIVER, DRIVER);
        settings.put(Environment.URL, URL);
        settings.put(Environment.USER, USERNAME);
        settings.put(Environment.PASS, PASSWORD);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "create-drop");

        configuration.setProperties(settings);
        return configuration;
    }
}




