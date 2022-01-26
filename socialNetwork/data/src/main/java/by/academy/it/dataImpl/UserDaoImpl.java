package by.academy.it.dataImpl;

import by.academy.it.dao.UserDao;
import by.academy.it.pojo.Role;
import by.academy.it.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.transform.sax.SAXResult;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    @Qualifier("socialNetworkSessionFactory")
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public List<User> readAllUser() {
        Session session = sessionFactory.getCurrentSession();
        List<User> userList =
                session.createQuery("from User", User.class).list();
        return userList;
    }

    @Override
    public User readUserById(String id) {
        return null;
    }

    @Override
    @Transactional
    public String findIdByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        String query = "select id FROM User where login like '" + login + "'";
        Object userObject = (Object) session.createQuery(query).getSingleResult();
        return userObject.toString();
    }

    @Override
    @Transactional
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    @Transactional
    public List<User> readUserByLogin(String userLogin) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM User where login like '" + userLogin + "'";
        List<User> userList = session.createQuery(query, User.class).list();
        return userList;
    }

    @Override
    @Transactional
    public List<User> readUserByEmail(String userEmail) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM User where email like '" + userEmail + "'";
        List<User> userList = session.createQuery(query, User.class).list();
        return userList;
    }

    @Override
    @Transactional
    public User getUserByToken(String sessionToken) {
        Session session = sessionFactory.getCurrentSession();

//        String hql = "SELECT userId, firstName,lastName,login,email,gender,profileImageName " +
//                "FROM User where sessionToken = : sessionToken";
//        Query query = session.createQuery(hql);
//        query.setParameter("sessionToken", sessionToken);
//        Object[] userObject = (Object[]) query.getSingleResult();
//        User user = new User();
//
//        if(userObject[0]!=null){
//            user.setUserId(userObject[0].toString());
//        }
//        if(userObject[1]!=null){
//            user.setFirstName(userObject[1].toString());
//        }
//        if(userObject[2]!=null){
//            user.setLastName(userObject[2].toString());
//        }
//        if(userObject[3]!=null){
//            user.setLogin(userObject[3].toString());
//        }
//        if(userObject[4]!=null){
//            user.setEmail(userObject[4].toString());
//        }
//        if(userObject[5]!=null){
//            user.setGender(userObject[5].toString());
//        }
//        if(userObject[6]!=null){
//            user.setProfileImageName(userObject[6].toString());
//        }

        String hql = "FROM User where sessionToken like '" + sessionToken + "'";
        User user = session.createQuery(hql, User.class).getSingleResult();
        return user;
    }

    @Override
    @Transactional
    public List<String> getSessionTokenAndUserRole(String id) {
        Session session = sessionFactory.getCurrentSession();
        String token = UUID.randomUUID().toString();
        Query setTokenQuery = session.createQuery("update User set sessionToken = :sessionToken where userId = :userId ");
        setTokenQuery.setParameter("userId", id);
        setTokenQuery.setParameter("sessionToken", token);
        setTokenQuery.executeUpdate();
        User user = session.get(User.class, id);
        Set<Role> userRoles = user.getRoles();
        String userRole = null;
        for (Role role : userRoles) {
            userRole = role.getName();
        }
        List<String> userDetails = new ArrayList<>();
        userDetails.add(token);
        userDetails.add(userRole);
        return userDetails;
    }


}
