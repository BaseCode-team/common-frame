package team.study.common.base.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * ErrorCode枚举
 *
 * @author JiaHao
 */

public enum ErrorCodeEnum {
    /**
     * @date 2022/11/26 22:40
     **/
    AUTH_ERROR("AUTH_ERROR", "权限异常", "AUTH_ERROR"),
    BIZ_ERROR("BIZ_ERROR", "业务异常", "BIZ_ERROR"),
    SYS_ERROR("SYS_ERROR", "系统异常", "SYS_ERROR"),
    THREAD_POOL_ERROR("THREAD_POOL_ERROR", "线程池任务异常", "THREAD_POOL_ERROR"),
    VALID_ERROR("VALID_ERROR", "校验异常", "VALID_ERROR");
    @Getter
    private final String key;
    @Getter
    private final String name;
    @Getter
    private final String code;

    ErrorCodeEnum(String key, String name, String code) {
        this.key = key;
        this.name = name;
        this.code = code;
    }

    /**
     * 根据枚举KEY获取枚举
     *
     * @param key 枚举KEY
     */
    public static ErrorCodeEnum getByKey(String key) {
        return Arrays.stream(values()).filter(e -> Objects.equals(key, e.key))
                .findFirst()
                .orElse(null);
    }
}
