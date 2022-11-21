package team.study.common.interaction.annotation;

import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * web请求注解
 *
 * @author JiaHao
 * @date 2022/11/20 18:33
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Web
@Controller
public @interface WebController {
}
