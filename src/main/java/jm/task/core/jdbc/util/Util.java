package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
   private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String PASSWORD = "root";
    private static final String USERNAME = "root";

    public Connection creatingConnection() {

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
}




