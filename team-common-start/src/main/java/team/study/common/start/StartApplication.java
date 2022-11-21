package team.study.common.start;

import java.lang.annotation.*;

/**
 * 启动类注解
 *
 * @author JiaHao
 * @date 2022/11/20 18:49
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface StartApplication {
}
