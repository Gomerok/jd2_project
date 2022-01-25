package by.academy.it.dataImpl;

import by.academy.it.dao.RoleDao;
import by.academy.it.pojo.Role;
import by.academy.it.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    @Qualifier("socialNetworkSessionFactory")
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public List<Role> readRoleByName(String roleName) {
        Session session = sessionFactory.getCurrentSession();
        List<Role> roles =
                session.createQuery("from Role where name like '"+roleName+"'", Role.class).list();
        return roles;
    }

    @Override
    @Transactional
    public int deleteRoleByName(String roleName) {
        Session session = sessionFactory.getCurrentSession();
        Query query =  session.createQuery("delete Role where name = :roleName");
        query.setParameter("roleName", roleName);
        int result = query.executeUpdate();
        return result;
    }
}


