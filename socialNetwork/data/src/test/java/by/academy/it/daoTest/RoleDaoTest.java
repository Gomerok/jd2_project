package by.academy.it.daoTest;

import by.academy.it.config.DaoConfiguration;
import by.academy.it.dao.RoleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = DaoConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RoleDaoTest {

    @Resource
    private RoleDao roleDao;

    @Test
    @Transactional
    @Rollback
    public void testReadSavedRoles() {
        assertNotNull(roleDao.readRoleByName("ROLE_ADMIN"));
        assertNotNull(roleDao.readRoleByName("ROLE_USER"));
    }
}