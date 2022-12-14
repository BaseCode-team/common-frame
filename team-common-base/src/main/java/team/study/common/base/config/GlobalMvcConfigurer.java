package team.study.common.base.config;

import lombok.AllArgsConstructor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team.study.common.base.interceptor.HeaderThreadLocalInterceptor;

/**
 * 公共配置类, 一些公共工具配置
 *
 * @author JiaHao
 * @date 2022-12-13 16:23
 */
@AllArgsConstructor
public class GlobalMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HeaderThreadLocalInterceptor())
                .addPathPatterns("/**")
                .order(-20);
    }
}
