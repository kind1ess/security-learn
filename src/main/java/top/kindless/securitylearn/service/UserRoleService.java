package top.kindless.securitylearn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.kindless.securitylearn.model.entity.RoleEntity;
import top.kindless.securitylearn.model.entity.UserRoleEntity;

import java.util.List;

public interface UserRoleService extends IService<UserRoleEntity> {

    List<RoleEntity> getRolesByUserId(Integer userId);

}
