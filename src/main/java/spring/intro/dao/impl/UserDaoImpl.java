package spring.intro.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.intro.dao.UserDao;
import spring.intro.model.User;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User get(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, userId);
//            Далі - довша версія. Працюють обидві. Довшу так само видалю на першу твою вимогу)))
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<User> cq = cb.createQuery(User.class);
//            Root<User> root = cq.from(User.class);
//            cq.where(cb.equal(root.get("id"), userId));
//            return session.createQuery(cq).uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Cannot show user from database", e);
        }
    }

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
