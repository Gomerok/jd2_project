//package by.academy.it.service;
//
//import by.academy.it.config.TestServiceSpringConfig;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.Assert.assertNotNull;
//
//@ContextConfiguration(classes = {TestServiceSpringConfig.class})
//@RunWith(SpringJUnit4ClassRunner.class)
//public class UserServiceTest {
//
//    @Autowired
//    UserService userService;
//
//
//    @Test
//    public void updatePassword() {
//        userService.updateUserPassword("password","4028c0817ed12046017ed12074d00000");
//    }
//}