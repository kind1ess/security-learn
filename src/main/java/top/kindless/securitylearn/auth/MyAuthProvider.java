package top.kindless.securitylearn.auth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import top.kindless.securitylearn.exception.AuthenticationFailException;
import top.kindless.securitylearn.model.entity.UserEntity;


@Component
public class MyAuthProvider implements AuthenticationProvider {

    private final UserDetailsService userService;
    private final PasswordEncoder encoder;

    public MyAuthProvider(@Qualifier("userServiceImpl") UserDetailsService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        String credentials = (String) authentication.getCredentials();
        UserEntity user = (UserEntity) userService.loadUserByUsername(principal);
        boolean matches = encoder.matches(credentials, user.getPassword());
        if (!matches){
            throw new AuthenticationFailException("用户名或密码错误");
        }
        return new UsernamePasswordAuthenticationToken(user,user.getPassword(),user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == UsernamePasswordAuthenticationToken.class;
    }
}
