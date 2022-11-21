package team.study.common.rpc.config.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team.study.common.rpc.config.ExceptionWrapper;

/**
 * feign异常配置
 *
 * @author JiaHao
 * @date 2022/11/20 18:38
 **/
@Configuration
public class FeignConfiguration {

    @Bean
    public ErrorDecoder feignErrorDecoder(ObjectMapper objectMapper, ExceptionWrapper exceptionWrapper) {
        return new FeignErrorDecoder(objectMapper, exceptionWrapper);
    }

}
