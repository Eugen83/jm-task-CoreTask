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
                statement.executeUpdate("CREATE TABLE users(id LONG PRIMARY KEY AUTO INCREMENT, user_name VARCHAR(45), " +
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
        try (Statement statement = connection.createStatement()) {
                boolean bool = statement.execute("INSERT INTO users(user_name, last_name, age) VALUES (name, lastName, age)");
                if (bool) System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        }
        


    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Statement statement = connection.createStatement()) {
                ResultSet resultSet1 = statement.executeQuery("SELECT * FROM users");
                while (resultSet1.next()) {
                    User user = new User();
                    user.setId(resultSet1.getLong("id"));
                    user.setName(resultSet1.getString("user_name"));
                    user.setLastName(resultSet1.getString("last_name"));
                    user.setAge(resultSet1.getByte("age"));
                    userList.add(user);
                }
        } catch (SQLException e) {
            System.out.println(e);;
        }
        return userList;
    }


        public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            System.out.println(e);

        }

    }
}
