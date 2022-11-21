package team.study.common.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 鉴权错误异常
 *
 * @author JiaHao
 * @date 2022/11/20 17:13
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthException extends RuntimeException {

    private String errorMessage;

    /**
     * 用给定的异常信息构造新实例。
     *
     * @param errorMessage 异常信息。
     */
    public AuthException(String errorMessage) {
        super((String) null);
        this.errorMessage = errorMessage;
    }

}
