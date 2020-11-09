package top.kindless.securitylearn.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.kindless.securitylearn.exception.AuthenticationFailException;
import top.kindless.securitylearn.utils.BaseResponse;

@RestControllerAdvice(basePackages = "top.kindless.securitylearn.controller")
@Slf4j
public class GlobalControllerExceptionHandler implements ResponseBodyAdvice<Object> {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public BaseResponse handleLoginErrorException(AuthenticationFailException exception){
        log.info(exception.getMessage());
        return BaseResponse.unAuthorized(exception.getLocalizedMessage(),null);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleException(Throwable t){
        log.error(t.getLocalizedMessage());
        t.printStackTrace();
        return BaseResponse.badRequest("服务器异常",null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public BaseResponse handleAccessDeniedException(AccessDeniedException e){
        log.info(e.getLocalizedMessage());
        return BaseResponse.unAuthorized(e.getLocalizedMessage(),null);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public BaseResponse beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Assert.notNull(o,"o must not be null");
        log.info(o.toString());
        if (o instanceof BaseResponse){
            return (BaseResponse) o;
        }
        return BaseResponse.ok(o);
    }
}
