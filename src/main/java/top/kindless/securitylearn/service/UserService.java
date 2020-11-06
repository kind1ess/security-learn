package top.kindless.securitylearn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.kindless.securitylearn.model.entity.UserEntity;

public interface UserService extends IService<UserEntity> {
    UserEntity getUserByUsername(String username);
}
