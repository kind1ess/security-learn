package top.kindless.securitylearn.controller;

import org.springframework.web.bind.annotation.*;
import top.kindless.securitylearn.model.entity.UserEntity;
import top.kindless.securitylearn.model.params.LoginParamImpl;
import top.kindless.securitylearn.service.LoginService;
import top.kindless.securitylearn.utils.JwtUtil;
import top.kindless.securitylearn.utils.UserUtil;

@RestController
@RequestMapping("/user")
public class UserController {

    private final LoginService loginService;

    public UserController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/info")
    public UserEntity getUserInfo(){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return (UserEntity) principal;
        return UserUtil.getCurrentUser();
    }

    @GetMapping("/token")
    public String getToken(){
        UserEntity userEntity = UserUtil.getCurrentUser();
//        System.out.println(principal);
        String jwt = JwtUtil.createJwt(userEntity.getId());
        return jwt;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginParamImpl loginParam){
        return loginService.login(loginParam);
    }

}
