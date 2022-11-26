package team.study.common.base.utils;

import team.study.common.base.exception.ValidationException;

import java.util.Collection;
import java.util.Objects;

/**
 * 校验工具类
 *
 * @author JiaHao
 * @date 2022/11/20 17:30
 **/
public class ValidationUtil {

    public static void sneakyThrow(String code, Object... params) {
        throw new ValidationException(code, params);
    }

    public static void isTrue(boolean expect, String message, Object... params) {
        if (!expect) {
            throw ValidationException.of(message, params);
        }
    }

    public static void isFalse(boolean expect, String message, Object... params) {
        isTrue(!expect, message, params);
    }

    public static void equals(String first, String second, String message, Object... params) {
        isTrue(first.equals(second), message, params);
    }

    public static void nil(Object object, String message, Object... params) {
        isTrue(Objects.isNull(object), message, params);
    }

    public static void notNull(Object object, String message, Object... params) {
        isTrue(Objects.nonNull(object), message, params);
    }

    public static void equals(Object first, Object second, String message, Object... params) {
        isTrue(Objects.equals(first, second), message, params);
    }

    public static void notEquals(Object first, Object second, String message, Object... params) {
        isTrue(!Objects.equals(first, second), message, params);
    }

    public static void empty(Collection collection, String message, Object... params) {
        isTrue(CollectionUtils.isEmpty(collection), message, params);
    }

    public static void notEmpty(Collection collection, String message, Object... params) {
        isTrue(CollectionUtils.isNotEmpty(collection), message, params);
    }

    public static void blank(String str, String message, Object... params) {
        isTrue(StringUtil.isBlank(str), message, params);
    }

    public static void notBlank(String str, String message, Object... params) {
        isTrue(StringUtil.isNotBlank(str), message, params);
    }

}
