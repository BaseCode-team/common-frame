package team.study.common.interaction.config;

import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import team.study.common.base.result.BaseResult;

/**
 * 链路追踪
 *
 * @author JiaHao
 * @date 2022/11/20 18:34
 **/
@ControllerAdvice
public class AddTraceIdResponseBodyAdvice implements ResponseBodyAdvice<BaseResult> {

    @Autowired
    private Tracer tracer;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return BaseResult.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public BaseResult beforeBodyWrite(BaseResult body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        body.setTraceId(tracer.currentSpan().context().traceIdString());
        return body;
    }
}
