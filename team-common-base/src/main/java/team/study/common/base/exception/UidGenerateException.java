package team.study.common.base.exception;

/**
 * UID异常
 *
 * @author JiaHao
 * @date 2022-12-13 14:36
 */
public class UidGenerateException extends BaseException {
    public UidGenerateException(String errMessage) {
        super(errMessage);
    }

    public UidGenerateException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public UidGenerateException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public UidGenerateException(String errCode, String errMessage, Throwable e) {
        super(errCode, errMessage, e);
    }

    /**
     * Constructor with message format
     *
     * @param msgFormat 格式化
     * @param args      参数
     */
    public UidGenerateException(String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
    }

}
