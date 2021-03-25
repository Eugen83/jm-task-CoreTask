package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String s ="CREATE TABLE IF NOT EXISTS users(Id int PRIMARY KEY AUTO_INCREMENT, user_name VARCHAR(45)," +
                    "last_name VARCHAR(100), age int)";
            Query query = session.createSQLQuery(s).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            session.close();
        }


    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(new User(name,lastName,age));
        transaction.commit();
        session.close();
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    @Transactional
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Object user = session.load(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<User> userList = session.createQuery("From " + User.class.getSimpleName()).list();
        transaction.commit();
        session.close();
        for(User user: userList){
            System.out.println(user);
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<User> userList= session.createCriteria(Entity.class).list();
        for (User user : userList){
            session.delete(user);
        }
        transaction.commit();
        session.close();

    }
}
