package top.kindless.securitylearn.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {

    @Test
    void test(){
//        System.out.println(JwtUtil.createJwt(1));
        System.out.println(JwtUtil.verifyJwt("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjA1MTY0OTM1fQ.XxQwglMwkIX_H-EdWo_WXc4kkm3LkyFNsfLGJFguJuk").get("id"));
    }

}
