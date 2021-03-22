package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.*;

import static jm.task.core.jdbc.util.Util.creatingConnection;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.creatingConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS users(Id int PRIMARY KEY AUTO_INCREMENT, user_name VARCHAR(45)," +
                    "last_name VARCHAR(100), age int)");

        } catch (SQLException e) {
            System.out.println(e);

        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("DROP TABLE IF EXISTS users");

        } catch (SQLException e) {
            System.out.println(e);

        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement pstatement = connection.prepareStatement("INSERT INTO users (user_name, last_name, age) VALUES (?, ?, ?)")) {
            pstatement.setString(1, name);
            pstatement.setString(2, lastName);
            pstatement.setByte(3, age);
            pstatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE Id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM users");
            while (resultSet1.next()) {
                User user = new User();
                user.setId(resultSet1.getLong("Id"));
                user.setName(resultSet1.getString("user_name"));
                user.setLastName(resultSet1.getString("last_name"));
                user.setAge(resultSet1.getByte("age"));
                userList.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
            ;
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
