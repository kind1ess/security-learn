package top.kindless.securitylearn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.kindless.securitylearn.dao.UserRoleMapper;
import top.kindless.securitylearn.model.entity.RoleEntity;
import top.kindless.securitylearn.model.entity.UserRoleEntity;
import top.kindless.securitylearn.service.UserRoleService;

import java.util.List;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {
    @Override
    public List<RoleEntity> getRolesByUserId(Integer userId) {
        return baseMapper.getRolesByUserId(userId);
    }
}
