package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        //UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
       userService.saveUser("Petr", "Petrov", (byte) 15);
        userService.saveUser("Ivan", "Ivanov", (byte) 45);
        userService.saveUser("Jonh", "Smith", (byte) 35);
        userService.saveUser("User", "Aftermath", (byte) 33);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
