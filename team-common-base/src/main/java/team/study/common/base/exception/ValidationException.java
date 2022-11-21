package team.study.common.base.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 校验异常
 *
 * @author JiaHao
 * @date 2022/11/20 17:15
 **/
@EqualsAndHashCode(callSuper = true)
public class ValidationException extends ServiceException {

    @Getter
    private Object[] params;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Object[] params) {
        super(message);
        this.params = params;
    }

    public ValidationException(String code, String message, Object[] params) {
        super(code, message);
        this.params = params;
    }

    public static ValidationException of(String code, Object[] params) {
        return new ValidationException(code, null, params);
    }

}

