package team.study.common.rpc.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign异常配置
 *
 * @author JiaHao
 * @date 2022/11/20 18:38
 **/
@Configuration
public class RpcConfiguration {

    @Bean
    @ConditionalOnMissingBean(ExceptionWrapper.class)
    public DefaultExceptionWrapper defaultExceptionWrapper() {
        return new DefaultExceptionWrapper();
    }

}
