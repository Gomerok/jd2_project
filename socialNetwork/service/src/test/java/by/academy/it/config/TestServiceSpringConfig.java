package by.academy.it.config;

import by.academy.it.dao.*;
import lombok.Getter;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Configuration
@ComponentScan(basePackages = "by.academy.it.service")
public class TestServiceSpringConfig {

    @Mock
    UserDao userDao;

    @Mock
    FriendDao friendDao;

    @Mock
    MessagesDao messagesDao;

    @Mock
    RoleDao roleDao;

    @Mock
    UserNewsDao userNewsDao;

    public TestServiceSpringConfig() {
        MockitoAnnotations.initMocks(this);
    }

    @Bean
    public UserDao employeeDao() {
        return userDao;
    }

    @Bean
    public FriendDao friendDao() {
        return friendDao;
    }

    @Bean
    public MessagesDao messagesDao() {
        return messagesDao;
    }

    @Bean
    public RoleDao roleDao() {
        return roleDao;
    }

    @Bean
    public UserNewsDao userNewsDao() {
        return userNewsDao;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}