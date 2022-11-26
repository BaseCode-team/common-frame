package team.study.common.base.exception;

import team.study.common.base.enums.ErrorCodeEnum;

import java.io.Serial;

/**
 * 业务异常
 *
 * @author JiaHao
 * @date 2022-11-22 15:31
 */
public class BizException extends BaseException {
    @Serial
    private static final long serialVersionUID = 1L;

    public BizException(String errMessage) {
        super(ErrorCodeEnum.BIZ_ERROR.getCode(), errMessage);
    }

    public BizException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public BizException(String errMessage, Throwable e) {
        super(ErrorCodeEnum.BIZ_ERROR.getCode(), errMessage, e);
    }

    public BizException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }
}
