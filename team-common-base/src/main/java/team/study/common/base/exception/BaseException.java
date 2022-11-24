package team.study.common.base.exception;

import java.io.Serial;

/**
 * 异常基类
 *
 * @author JiaHao
 * @date 2022-11-22 15:30
 */
public abstract class BaseException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private String errCode;

    public BaseException(String errMessage) {
        super(errMessage);
    }

    public BaseException(String errCode, String errMessage) {
        super(errMessage);
        this.errCode = errCode;
    }

    public BaseException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public BaseException(String errCode, String errMessage, Throwable e) {
        super(errMessage, e);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
