package top.kindless.securitylearn.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role")
public class RoleEntity {

    private Integer id;

    private String roleName;

    private String roleDescription;

}
