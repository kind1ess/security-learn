package top.kindless.securitylearn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.kindless.securitylearn.model.entity.RoleEntity;
import top.kindless.securitylearn.model.entity.UserRoleEntity;

import java.util.List;

@Repository
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

    List<RoleEntity> getRolesByUserId(@Param("userId") Integer userId);

}
