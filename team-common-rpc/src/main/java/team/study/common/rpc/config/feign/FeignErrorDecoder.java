package team.study.common.rpc.config.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import team.study.common.base.exception.ExceptionFactory;
import team.study.common.base.response.Response;
import team.study.common.rpc.config.ExceptionWrapper;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * 请求错误编码解析
 * 服务端能处理的异常返回都是500，如果是其他的，那么就是无法处理的。
 *
 * @author JiaHao
 * @date 2022/11/20 18:39
 **/
@Slf4j
@AllArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    private final ExceptionWrapper exceptionWrapper;

    @Override
    public Exception decode(String methodKey, feign.Response response) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.value() != response.status()) {
            String message = MessageFormat.format("响应异常, code:{0}, reason:{1}", response.status(), response.reason());
            throw ExceptionFactory.sysException(message);
        }

        Response result = decodeResponseAsResult(methodKey, response);

        // 如果Result为空，那么返回系统异常
        if (Objects.isNull(result)) {
            log.debug("响应异常, response=====> status:{}, reason:{}, 可能由于未经过GlobalExceptionHandler处理", response.status(), response.reason());
            return wrap(ExceptionFactory.sysException(response.reason()));
        }
        // 否则就返回服务业务异常
        return wrap(ExceptionFactory.bizException(result.getCode(), result.getErrMessage()));
    }

    private Response decodeResponseAsResult(String methodKey, feign.Response response) {
        Response result = null;
        try {
            // 如果时500的响应吗，尝试解析响应内容为Result
            result = objectMapper.readValue(response.body().asInputStream(), Response.class);
        } catch (IOException e) {
            log.error("解析feign错误响应失败methodKey:{},response:{}", methodKey, response);
            log.error("异常信息", e);
        }
        return result;
    }

    private Exception wrap(Exception exception) {
        return exceptionWrapper.wrap(exception);
    }

    private final ErrorDecoder defaultErrorDecoder = new Default();

}
