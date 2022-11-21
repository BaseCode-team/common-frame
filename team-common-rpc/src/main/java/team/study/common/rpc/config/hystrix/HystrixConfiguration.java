package team.study.common.rpc.config.hystrix;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team.study.common.rpc.config.ExceptionWrapper;
import team.study.common.rpc.config.RpcConfiguration;

/**
 * @author JiaHao
 */
@Configuration
@ConditionalOnBean(type = "org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerConfiguration")
@AutoConfigureBefore(RpcConfiguration.class)
public class HystrixConfiguration {

    @Bean
    public ExceptionWrapper hystrixExceptionWrapper() {
        return new HystrixExceptionWrapper();
    }

}
