package team.study.common.base.exception;


/**
 * 系统异常
 *
 * @author JiaHao
 * @date 2022/11/20 17:14
 **/
public class SystemException extends ServiceException {

    public SystemException() {
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

}
