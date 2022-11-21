package team.study.common.interaction.annotation;

import java.lang.annotation.*;

/**
 * rpc请求注解
 *
 * @author JiaHao
 * @date 2022/11/20 18:03
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Rpc {
}
