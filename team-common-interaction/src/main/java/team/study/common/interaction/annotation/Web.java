package team.study.common.interaction.annotation;

import java.lang.annotation.*;

/**
 * web请求注解
 *
 * @author JiaHao
 * @date 2022/11/20 18:32
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Web {
}
