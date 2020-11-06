package top.kindless.securitylearn.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_role")
public class UserRoleEntity {

    private Integer id;

    private Integer userId;

    private Integer roleId;

}
