package top.kindless.securitylearn.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.kindless.securitylearn.dao.UserRoleMapper;

@SpringBootTest
public class UserRoleMapperTest {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Test
    void testGetRoleByUserId(){
        System.out.println(userRoleMapper.getRolesByUserId(1));
    }

}
