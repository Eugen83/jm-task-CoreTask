package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();
    private Connection connection = util.creatingConnection();
    private ResultSet resultSet = null;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Statement statement = connection.createStatement()) {
            DatabaseMetaData metaData = connection.getMetaData();
            resultSet = metaData.getTables(null, null, "users", null);
            if (!resultSet.next()) {
                statement.executeUpdate("CREATE TABLE users(id LONG PRIMARY KEY AUTO INCREMENT, name VARCHAR(45), " +
                        "last_name VARCHAR(100), age BYTE");
            } else {
                System.out.println("The table You're trying to create is already exist!");
            }
        } catch (SQLException e) {
            System.out.println(e);

        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            DatabaseMetaData metaData = connection.getMetaData();
            resultSet = metaData.getTables(null, null, "users", null);
            if (resultSet.next()) {
                statement.executeUpdate("DROP TABLE users");
            } else {
                System.out.println("there aren't the table You're trying to delete");
            }
        } catch (SQLException e) {
            System.out.println(e);

        }

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
