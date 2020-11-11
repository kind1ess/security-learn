package top.kindless.securitylearn.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import top.kindless.securitylearn.model.entity.UserEntity;

@Slf4j
public class UserUtil {

    public static UserEntity getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("当前authentication的类型是：{}",authentication.getClass().getName());
        return (UserEntity) authentication.getPrincipal();
    }
}
