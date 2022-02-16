package by.academy.it.service;

import by.academy.it.dao.UserDao;
import by.academy.it.dto.UserValidDto;
import by.academy.it.pojo.User;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setId("4028c0817eb53bc3017eb55c8fb30000");
        user.setEmail("email@email.com");
        user.setLogin("user");
        user.setPassword("$2a$12$Mj18A.MKWpeCWa05wQI9eO7TjxYdEEAVHrmgZZ4E.evH4iVZnVdPi");

        UserValidDto userValidDto = new UserValidDto();
        user.setId("4028c0817eb53bc3017eb55c8fb30000");
        user.setEmail("email@email.com");
        user.setLogin("user");
        user.setPassword("$2a$12$Mj18A.MKWpeCWa05wQI9eO7TjxYdEEAVHrmgZZ4E.evH4iVZnVdPi");


    }
}
