package top.kindless.securitylearn.service;

import org.springframework.lang.NonNull;
import top.kindless.securitylearn.model.params.LoginParam;

public interface LoginService {

    /**
     * 登陆逻辑处理
     * @param loginParam 登陆参数，不含验证码
     * @return token令牌
     */
    @NonNull String login(@NonNull LoginParam loginParam);

}
