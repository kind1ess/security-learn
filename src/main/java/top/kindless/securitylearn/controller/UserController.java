package top.kindless.securitylearn.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kindless.securitylearn.model.entity.UserEntity;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public UserEntity getUserInfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (UserEntity) principal;
    }

}
