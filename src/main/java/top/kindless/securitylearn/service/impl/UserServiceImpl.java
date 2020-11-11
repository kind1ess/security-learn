package top.kindless.securitylearn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.kindless.securitylearn.dao.UserMapper;
import top.kindless.securitylearn.model.entity.RoleEntity;
import top.kindless.securitylearn.model.entity.UserEntity;
import top.kindless.securitylearn.service.UserRoleService;
import top.kindless.securitylearn.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService, UserDetailsService {

    private final UserRoleService userRoleService;

    public UserServiceImpl(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }


    @Override
    public UserEntity getUserByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<UserEntity>().lambda().eq(UserEntity::getUsername,username));
    }

    @Override
    public UserEntity loadUserById(Integer id) {
        UserEntity userEntity = getById(id);
        if (userEntity == null){
            return null;
        }
        List<RoleEntity> roles = userRoleService.getRolesByUserId(id);
        List<GrantedAuthority> authorities = roles.stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getRoleName()))
                .collect(Collectors.toList());
        userEntity.setAuthorities(authorities);
        return userEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = getUserByUsername(username);
        if (userEntity == null)
            throw new UsernameNotFoundException("用户不存在");
        List<RoleEntity> roles = userRoleService.getRolesByUserId(userEntity.getId());
        List<GrantedAuthority> authorities = roles.stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getRoleName()))
                .collect(Collectors.toList());
        userEntity.setAuthorities(authorities);
        return userEntity;
    }

}
