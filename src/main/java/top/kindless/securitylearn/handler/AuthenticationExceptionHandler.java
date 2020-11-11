package top.kindless.securitylearn.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.kindless.securitylearn.utils.BaseResponse;
import top.kindless.securitylearn.utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.warn(e.getMessage());
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(JsonUtil.convertToJson(BaseResponse.unAuthorized(e.getLocalizedMessage(),null)));
    }
}
