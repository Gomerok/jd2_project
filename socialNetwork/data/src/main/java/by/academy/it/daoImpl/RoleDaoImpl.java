package by.academy.it.daoImpl;

import by.academy.it.dao.RoleDao;
import by.academy.it.pojo.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository

public class RoleDaoImpl implements RoleDao {

    final static Logger logger = Logger.getLogger(RoleDaoImpl.class.getName());

    @Autowired
    @Qualifier("socialNetworkSessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Serializable saveRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        Serializable id = session.save(role);
        logger.info("Role (" + role + ") had been saved.");
        return id;
    }

    @Override
    public List<Role> readRoleByName(String roleName) {
        Session session = sessionFactory.getCurrentSession();
        List<Role> roles =
                session.createQuery("from Role where name like '" + roleName + "'", Role.class).list();
        logger.info("Read role by name('" + roleName + "'). Role: " + roles);
        return roles;
    }

    @Override
    public int deleteRoleByName(String roleName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Role where name = :roleName");
        query.setParameter("roleName", roleName);
        int deletedRows = query.executeUpdate();
        logger.info("Deleted role by name(id: " + roleName + "). Deleted rows: " + deletedRows);
        return deletedRows;
    }
}


