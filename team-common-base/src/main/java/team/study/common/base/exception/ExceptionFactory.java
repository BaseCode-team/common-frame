package team.study.common.base.exception;

/**
 * 异常工厂
 *
 * @author JiaHao
 * @date 2022-11-22 15:53
 */
public class ExceptionFactory {
    public ExceptionFactory() {
    }

    public static BizException bizException(String errorMessage) {
        return new BizException(errorMessage);
    }

    public static BizException bizException(String errorCode, String errorMessage) {
        return new BizException(errorCode, errorMessage);
    }

    public static SysException sysException(String errorMessage) {
        return new SysException(errorMessage);
    }

    public static SysException sysException(String errorCode, String errorMessage) {
        return new SysException(errorCode, errorMessage);
    }

    public static SysException sysException(String errorMessage, Throwable e) {
        return new SysException(errorMessage, e);
    }

    public static SysException sysException(String errorCode, String errorMessage, Throwable e) {
        return new SysException(errorCode, errorMessage, e);
    }

    public static ThreadPoolExecutorException threadPoolExecutorException(Throwable e) {
        return new ThreadPoolExecutorException(e);
    }

    public static ValidationException validationException(String message) {
        return new ValidationException(message);
    }

    public static ValidationException validationException(String message, Object[] params) {
        return new ValidationException(message, params);
    }

    public static ValidationException validationException(String code, String message, Object[] params) {
        return new ValidationException(code, message, params);
    }
}