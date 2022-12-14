package team.study.common.base.handle;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import team.study.common.base.annotations.IgnoreResponseBodyAdvice;
import team.study.common.base.config.IgnoreProperties;
import team.study.common.base.response.Response;
import team.study.common.base.response.SingleResponse;

import java.util.Objects;

/**
 * 全局响应体包装
 *
 * @author JiaHao
 * @date 2022-12-13 18:28
 */

public class AbstractGlobalResponseBodyAdvice implements ResponseBodyAdvice {
    private final IgnoreProperties ignoreProperties;

    public AbstractGlobalResponseBodyAdvice(IgnoreProperties ignoreProperties) {
        this.ignoreProperties = ignoreProperties;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        // 类上如果被 IgnoreResponseBodyAdvice 标识就不拦截
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseBodyAdvice.class)) {
            return false;
        }

        // 方法上被标注也不拦截
        return !Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(IgnoreResponseBodyAdvice.class);
    }

    protected boolean isIgnore(String path) {
        return ignoreProperties.isIgnoreToken(path);
    }

    @Override
    public Object beforeBodyWrite(Object o, @NotNull MethodParameter methodParameter, @NotNull MediaType mediaType, @NotNull Class aClass, @NotNull ServerHttpRequest serverHttpRequest, @NotNull ServerHttpResponse serverHttpResponse) {
        if (o == null) {
            return null;
        }
        if (o instanceof Response || isIgnore(serverHttpRequest.getURI().getPath())) {
            return o;
        }

        return SingleResponse.of(o);
    }
}
