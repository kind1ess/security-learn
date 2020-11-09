package top.kindless.securitylearn.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kindless.securitylearn.model.entity.UserEntity;
import top.kindless.securitylearn.utils.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public UserEntity getUserInfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (UserEntity) principal;
    }

    @GetMapping("/token")
    public String getToken(){
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(principal);
        String jwt = JwtUtil.createJwt(userEntity.getId());
        return jwt;
    }

}
