package spring.intro.dao.impl;

import spring.intro.dao.UserDao;
import spring.intro.model.User;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            long id = (Long) session.save(user);
            transaction.commit();
            user.setId(id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot add user to database", e);
        }
    }

    @Override
    public List<User> listUsers() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<User> query = session.getCriteriaBuilder().createQuery(User.class);
            query.from(User.class);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot show all users from database", e);
        }
    }
}
