package top.kindless.securitylearn.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void testGetUserByUsername(){
        System.out.println(userService.getUserByUsername("kindless"));
    }

}
