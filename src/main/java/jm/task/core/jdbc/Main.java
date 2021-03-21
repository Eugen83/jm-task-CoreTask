package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();
        userDao.saveUser("Petr", "Petrov", (byte) 15);
        userDao.saveUser("Ivan", "Ivanov", (byte) 45);
        userDao.saveUser("Jonh", "Smith", (byte) 35);
        userDao.saveUser("User", "Aftermath", (byte) 33);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
