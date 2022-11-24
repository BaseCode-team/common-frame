package team.study.common.interaction.config;

import brave.Tracer;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import team.study.common.base.response.Response;

/**
 * 链路追踪
 *
 * @author JiaHao
 * @date 2022/11/20 18:34
 **/
@ControllerAdvice
public class AddTraceIdResponseBodyAdvice implements ResponseBodyAdvice<Response> {

    @Autowired
    private Tracer tracer;

    @Override
    public boolean supports(MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return Response.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Response beforeBodyWrite(Response body, @NotNull MethodParameter returnType, @NotNull MediaType selectedContentType, @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        assert body != null;
        body.setTraceId(tracer.currentSpan().context().traceIdString());
        return body;
    }
}
