package team.study.common.base.aop;

import java.lang.annotation.*;

/**
 * @author JiaHao
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebLog {
    /**
     * 描述信息
     *
     * @date 2022/11/17 12:14
     **/
    String description() default "";
}
