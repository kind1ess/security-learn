package top.kindless.securitylearn.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.kindless.securitylearn.exception.AuthenticationFailException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "top.kindless.securitylearn.controller")
@Slf4j
public class GlobalControllerExceptionHandler implements ResponseBodyAdvice<Object> {

//    @ExceptionHandler(AuthenticationFailException.class)
//    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
//    public Map<String,Object> handleLoginErrorException(AuthenticationFailException exception){
////        log.info(exception.getMessage());
//        return new HashMap<String, Object>(){
//            {
//                put("code",exception.getHttpStatus().value());
//                put("msg",exception.getMessage());
//                put("data",exception.getErrorData());
//            }
//        };
//    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Assert.notNull(o,"o must not be null");
        log.info(o.toString());
        return new HashMap<String,Object>(){
            {
                put("code",200);
                put("msg","ok");
                put("data",o);
            }
        };
    }
}
