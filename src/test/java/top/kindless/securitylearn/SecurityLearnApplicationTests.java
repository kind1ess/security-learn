package top.kindless.securitylearn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@SpringBootTest
class SecurityLearnApplicationTests {

	@Test
	void contextLoads() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("24112411kkddss"));
	}

}
