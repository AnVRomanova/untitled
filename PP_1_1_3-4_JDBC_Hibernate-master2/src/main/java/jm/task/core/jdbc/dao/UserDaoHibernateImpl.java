package jm.task.core.jdbc.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jm.task.core.jdbc.bl.HibernateUtil;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.sql.*;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static SessionFactory sessionFactory;
    protected EntityManager manager;

    public UserDaoHibernateImpl() {

    }

    //@Override
    public void createUsersTable() throws SQLException {
    }

   // @Override
    public void dropUsersTable() {
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Object persistentInstance = session.get(User.class, id);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
        }

        session.getTransaction().commit();
        System.out.printf("deleted user № " + id );
    }

   @Override
    public List<User> getAllUsers() {
       Session session = HibernateUtil.getSessionFactory().openSession();
       session.getTransaction().begin();

       CriteriaBuilder builder = session.getCriteriaBuilder();
       CriteriaQuery<User> criteria = builder.createQuery(User.class);
       criteria.from(User.class);
       List<User> data = session.createQuery(criteria).getResultList();
       return data;

    }

   // @Override
    public void cleanUsersTable() {
    }
}
