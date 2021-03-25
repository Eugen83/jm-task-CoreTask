package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

private Transaction transaction = null;
private SessionFactory sf = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            String s = "CREATE TABLE IF NOT EXISTS users(Id int PRIMARY KEY AUTO_INCREMENT, user_name VARCHAR(45)," +
                    "last_name VARCHAR(100), age int)";
            Query query = session.createSQLQuery(s).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }


    @Override
    public void dropUsersTable() {
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Transactional
    public void removeUserById(long id) {
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            Object user = session.load(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            userList = session.createQuery("From " + User.class.getSimpleName()).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        System.out.println(userList);
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from " + User.class.getSimpleName());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
