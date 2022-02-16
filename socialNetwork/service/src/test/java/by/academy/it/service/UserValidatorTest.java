package by.academy.it.service;

import by.academy.it.dao.UserDao;
import by.academy.it.pojo.User;
import by.academy.it.validator.UserValidator;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserValidatorTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserValidator userValidator;

    public UserValidatorTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkUserPassword() {
        User user = new User();
        user.setId("4028c0817eb53bc3017eb55c8fb30000");
        user.setEmail("email@email.com");
        user.setLogin("user");
        user.setPassword("$2a$12$Mj18A.MKWpeCWa05wQI9eO7TjxYdEEAVHrmgZZ4E.evH4iVZnVdPi");

        given(userDao.readUserById("4028c0817eb53bc3017eb55c8fb30000")).willReturn(user);
        boolean b = userValidator.checkPassword("admin", "4028c0817eb53bc3017eb55c8fb30000");

        verify(userDao).readUserById("4028c0817eb53bc3017eb55c8fb30000");

        assertTrue(b);
    }

}