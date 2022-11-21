package team.study.common.base.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 是否判断枚举
 *
 * @author JiaHao
 * @date 2022/11/20 17:11
 **/
public enum YesNoEnum {
    /**
     * 是否判断枚举
     */

    YES(1, "是", Boolean.TRUE),
    NO(0, "否", Boolean.FALSE),
    ;

    @Getter
    private final Integer key;
    @Getter
    private final String name;
    @Getter
    private final Boolean value;

    YesNoEnum(Integer key, String name, Boolean value) {
        this.key = key;
        this.name = name;
        this.value = value;
    }

    /**
     * 根据枚举KEY获取枚举
     *
     * @param key 枚举KEY
     */
    public static YesNoEnum getByKey(Integer key) {
        return Arrays.stream(values()).filter(e -> Objects.equals(key, e.key))
                .findFirst()
                .orElse(null);
    }

}
