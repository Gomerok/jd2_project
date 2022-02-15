package by.academy.it.daoTest;

import by.academy.it.config.DaoConfiguration;
import by.academy.it.dao.FriendDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@ContextConfiguration(classes = DaoConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FriendDaoTest {

    @Resource
    FriendDao friendDao;

    @Test
    public void testSaveFriends() {
    }

    public void testDeleteFriends() {
    }

    public void testReadFriendsByUserId() {
    }

    public void testReadAllFriendsByUserId() {
    }

    public void testUpdateFriends() {
    }

    public void testReadAllFriends() {
    }

    public void testGetFriend() {
    }
}