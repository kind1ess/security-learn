package top.kindless.securitylearn.auth.filter;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import top.kindless.securitylearn.auth.JwtAuthToken;
import top.kindless.securitylearn.exception.AuthenticationFailException;
import top.kindless.securitylearn.handler.AuthenticationExceptionHandler;
import top.kindless.securitylearn.model.entity.UserEntity;
import top.kindless.securitylearn.service.UserService;
import top.kindless.securitylearn.utils.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final AuthenticationExceptionHandler authenticationExceptionHandler;

    public JwtAuthFilter(UserService userService, AuthenticationExceptionHandler authenticationExceptionHandler) {
        this.userService = userService;
        this.authenticationExceptionHandler = authenticationExceptionHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            Integer userId = getUserId(request);
            if (userId == null){
                chain.doFilter(request,response);
                return;
            }
            if (authenticateIsRequired()){
                doAuth(userId);
            }
        } catch (AuthenticationException e) {
            log.info(e.getLocalizedMessage());
            SecurityContextHolder.clearContext();
            authenticationExceptionHandler.commence(request,response,e);
            return;
        }
        chain.doFilter(request,response);
    }

    private Integer getUserId(HttpServletRequest request){
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!authHeader.startsWith("Bearer ")){
            return null;
        }
        Claims claims = JwtUtil.verifyJwt(authHeader.substring(6));
        if (claims == null){
            throw new AuthenticationFailException("Json web token错误");
        }
        return claims.get("id",Integer.class);
    }

    private boolean authenticateIsRequired(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()){
            return true;
        }

        if (authentication instanceof AnonymousAuthenticationToken){
            return true;
        }
        return false;
    }

    private void doAuth(@NonNull Integer id){
        UserEntity userEntity = userService.loadUserById(id);
        if (userEntity == null)
            throw new AuthenticationFailException("Json web token错误");
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthToken(userEntity,userEntity.getAuthorities()));
    }
}
