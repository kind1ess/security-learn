package top.kindless.securitylearn.model.params;

public interface LoginParamWithCaptcha extends LoginParam{

    String getCaptcha();

    String getCaptchaKey();

}
