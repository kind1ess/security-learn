package top.kindless.securitylearn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;

public class AuthenticationFailException extends BadCredentialsException implements ErrorData {

    private Object errorData;

    public AuthenticationFailException(String message) {
        super(message);
    }

    public AuthenticationFailException(String message, Throwable t) {
        super(message, t);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

    @Override
    public Object getErrorData() {
        return errorData;
    }

    @Override
    public AuthenticationFailException setErrorData(Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
