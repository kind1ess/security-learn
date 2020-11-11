package top.kindless.securitylearn.model.params;

import lombok.Data;

@Data
public class LoginParamImpl implements LoginParam{

    private String username;

    private String password;

}
