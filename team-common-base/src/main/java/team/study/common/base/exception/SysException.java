package team.study.common.base.exception;

import team.study.common.base.enums.ErrorCodeEnum;

import java.io.Serial;

/**
 * 系统异常
 *
 * @author JiaHao
 * @date 2022-11-22 15:32
 */
public class SysException extends BaseException {
    @Serial
    private static final long serialVersionUID = 1L;

    public SysException(String errMessage) {
        super(ErrorCodeEnum.SYS_ERROR.getCode(), errMessage);
    }

    public SysException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public SysException(String errMessage, Throwable e) {
        super(ErrorCodeEnum.SYS_ERROR.getCode(), errMessage, e);
    }

    public SysException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }
}
