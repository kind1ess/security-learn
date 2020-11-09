package top.kindless.securitylearn.controller;

import org.apache.catalina.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.kindless.securitylearn.model.entity.UserEntity;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String hello(){
        return "hello";
    }

}
