package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String PASSWORD = "root";
    private static final String USERNAME = "root";

    public static Connection creatingConnection() {

        Driver driver;
        Connection connection = null;

        {
            try {
                driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return connection;
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, USERNAME);
                properties.put(Environment.PASS, PASSWORD);
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e){
                System.err.println(e);
            }
        }
        return sessionFactory;
    }

}




