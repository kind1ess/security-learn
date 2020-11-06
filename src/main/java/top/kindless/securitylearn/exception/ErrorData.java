package top.kindless.securitylearn.exception;

import org.springframework.http.HttpStatus;

public interface ErrorData {

    HttpStatus getHttpStatus();

    Object getErrorData();

    Object setErrorData(Object errorData);

}
