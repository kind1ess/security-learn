package top.kindless.securitylearn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "top.kindless.securitylearn.dao")
public class SecurityLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityLearnApplication.class, args);
	}

}
