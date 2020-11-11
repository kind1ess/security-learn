package top.kindless.securitylearn.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import top.kindless.securitylearn.model.entity.UserEntity;
import top.kindless.securitylearn.model.params.LoginParam;
import top.kindless.securitylearn.service.LoginService;
import top.kindless.securitylearn.utils.JwtUtil;

@Service
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;

    public LoginServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(LoginParam loginParam) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginParam.getUsername(), loginParam.getPassword()));
        return JwtUtil.createJwt(((UserEntity)authenticate.getPrincipal()).getId());
    }
}
