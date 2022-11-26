package team.study.common.base.exception;

import lombok.EqualsAndHashCode;
import team.study.common.base.enums.ErrorCodeEnum;

import java.io.Serial;

/**
 * 鉴权错误异常
 *
 * @author JiaHao
 * @date 2022/11/20 17:13
 **/

@EqualsAndHashCode(callSuper = true)
public class AuthException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AuthException(String errMessage) {
        super(ErrorCodeEnum.AUTH_ERROR.getCode(), errMessage);
    }

    public AuthException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public AuthException(String errMessage, Throwable e) {
        super(ErrorCodeEnum.AUTH_ERROR.getCode(), errMessage, e);
    }

    public AuthException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }
}
