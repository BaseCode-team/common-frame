package team.study.common.interaction.annotation;

import org.springframework.web.bind.annotation.RestController;

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
@RestController
public @interface WebRestController {
}
