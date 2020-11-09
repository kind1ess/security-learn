package top.kindless.securitylearn.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    private Integer code;

    private String msg;

    private Object data;

    public static BaseResponse ok(Object data){
        return new BaseResponse(HttpStatus.OK.value(), "ok",data);
    }

    public static BaseResponse internalServerError(String msg,Object data){
        return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),msg,data);
    }

    public static BaseResponse badRequest(String msg,Object data){
        return new BaseResponse(HttpStatus.BAD_REQUEST.value(), msg,data);
    }

    public static BaseResponse unAuthorized(String msg,Object data){
        return new BaseResponse(HttpStatus.UNAUTHORIZED.value(), msg,data);
    }

    public static BaseResponse forbidden(String msg,Object data){
        return new BaseResponse(HttpStatus.FORBIDDEN.value(), msg,data);
    }

    public static BaseResponse build(HttpStatus status,String msg,Object data){
        return new BaseResponse(status.value(),msg,data);
    }

}
