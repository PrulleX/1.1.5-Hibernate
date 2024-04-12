package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        String sqlRequest = "CREATE TABLE IF NOT EXISTS users (" + "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(150) NOT NULL," + "lastname VARCHAR(150) NOT NULL," + "age INT NOT NULL" + ")";

        try (Session session = getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sqlRequest).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        String sqlRequest = "DROP TABLE IF EXISTS users";

        try (Session session = getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sqlRequest).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        String sqlRequest = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try (Session session = getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sqlRequest)
                    .setParameter(1, name)
                    .setParameter(2, lastName)
                    .setParameter(3, age)
                    .executeUpdate();

            transaction.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

        String sqlRequest = "DELETE FROM users WHERE id = ?";

        try(Session session = getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sqlRequest)
                    .setParameter(1, id)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sqlRequest = "SELECT id, name, age, lastname FROM users";

        try(Session session = getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery(sqlRequest);

            sqlQuery.addEntity(User.class);
            users = sqlQuery.list();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(User user : users) System.out.println(user.toString());
        return users;
    }

    @Override
    public void cleanUsersTable() {

        String sqlRequest = "DELETE FROM users";

        try(Session session = getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sqlRequest).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
