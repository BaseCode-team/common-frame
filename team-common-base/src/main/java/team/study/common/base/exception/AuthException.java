package team.study.common.base.exception;

import lombok.EqualsAndHashCode;

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
    private static final String DEFAULT_ERR_CODE = "AUTH_ERROR";

    public AuthException(String errMessage) {
        super(DEFAULT_ERR_CODE, errMessage);
    }

    public AuthException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public AuthException(String errMessage, Throwable e) {
        super(DEFAULT_ERR_CODE, errMessage, e);
    }

    public AuthException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }
}
