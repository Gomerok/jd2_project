package by.academy.it.daoImpl;

import by.academy.it.dao.UserDao;
import by.academy.it.pojo.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    final static Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

    @Autowired
    @Qualifier("socialNetworkSessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<User> getUsersBySearchParam(String searchParam, int pageId, int pageSize) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from User where ( firstName like '" + searchParam +
                "%' or lastName like '" + searchParam + "%' ) ", User.class);
        query.setFirstResult(calculateOffset(pageId, pageSize));
        query.setMaxResults(pageSize);
        List<User> personList = query.list();
        logger.info("Search " + pageSize + " users by search param ('" + searchParam + "').Users list: " + personList);
        return personList;
    }

    private int calculateOffset(int page, int pageSize) {
        return ((pageSize * page) - pageSize);
    }

    @Override
    public long countUser(String searchParam) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createQuery("select count(u.id) from User u where ( firstName like '" + searchParam +
                        "%' or lastName like '" + searchParam + "%' )");
        logger.info("Count users by search param ('" + searchParam + "').Count: " + query.uniqueResult());
        return (Long) query.uniqueResult();
    }

    @Override
    public int updateActivitiStatus(Serializable id, String activitiStatus) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update User set activitiStatus = :activitiStatus where id = :id ");
        query.setParameter("id", id);
        query.setParameter("activitiStatus", activitiStatus);
        int updatedRows = query.executeUpdate();
        logger.info("Update user(id: " + id + ") activity status. New status: " + activitiStatus + " Updated rows: " + updatedRows);
        return updatedRows;
    }

    @Override
    public String readActivitiStatus(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select activitiStatus from User where id = :id ");
        query.setParameter("id", id);
        String activitiStatus = (String) query.uniqueResult();
        logger.info("Read activity status by user(id: " + id + "). Activity status:" + activitiStatus);
        return activitiStatus;
    }

    @Override
    public int updatePassword(String password, Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update User set password = :password where id = :id ");
        query.setParameter("id", id);
        query.setParameter("password", password);
        int updatedRows = query.executeUpdate();
        logger.info("Update password by user(id: " + id + "). Updated rows: " + updatedRows);
        return updatedRows;
    }

    @Override
    public List<User> readUsersByRole(String userRole) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from User u join u.roles r where r.name = :roleName", User.class);
        query.setParameter("roleName", userRole);
        List<User> users = query.list();
        logger.info("Read users by role('" + userRole + "'). Users list: " + users);
        return users;
    }

    @Override
    public Serializable saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Serializable id = session.save(user);
        logger.info("User (" + user + ") had been saved.");
        return id;
    }

    @Override
    public List<User> readAllUser() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users =
                session.createQuery("from User", User.class).list();
        logger.info("Read all users. Users list: " + users);
        return users;
    }

    @Override
    public User readUserById(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        logger.info("Read user by id(id: '" + id + "'). User: " + user);
        return user;
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println(user);
        session.update(user);
        logger.info("Update user: " + user);
    }

    @Override
    public void deleteUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Serializable id = user.getId();
        session.delete(user);
        User loadedUser = session.get(User.class, id);
        if (loadedUser == null) {
            logger.info("User had been deleted: " + user);
        }
    }

    @Override
    public User readUserByLogin(String userLogin) {
        Session session = sessionFactory.getCurrentSession();
        String query = "from User where login like '" + userLogin + "'";
        List<User> users = session.createQuery(query, User.class).list();
        if (users.isEmpty()) {
            return null;
        }
        logger.info("Read user by login('" + userLogin + "'). User: " + users.get(0));
        return users.get(0);
    }

    @Override
    public User readUserByEmail(String userEmail) {
        Session session = sessionFactory.getCurrentSession();
        String query = "from User where email like '" + userEmail + "'";
        List<User> users = session.createQuery(query, User.class).list();
        if (users.isEmpty()) {
            return null;
        }
        logger.info("Read user by email('" + userEmail + "'). User:" + users.get(0));
        return users.get(0);
    }

}
