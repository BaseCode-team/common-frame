package team.study.common.base.aop;

import java.lang.annotation.*;

/**
 * @author JiaHao
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepositoryLog {
    /**
     * 描述信息
     **/
    String description() default "";
}
