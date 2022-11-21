package team.study.common.rpc.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import team.study.common.api.api.DemoApi;
import team.study.common.rpc.config.DemoConstants;

/**
 * @author JiaHao
 * @date 2022/11/20 18:39
 **/
@FeignClient(
        name = DemoConstants.Demo.SERVICE_NAME,
        contextId = DemoConstants.Demo.NAME,
        fallback = DemoApiClient.AccessFallback.class,
        configuration = DemoApiClient.AccessFallback.class
)
public interface DemoApiClient extends DemoApi {

    @Component
    class AccessFallback implements DemoApiClient {

        @Override
        public void delete(Long id) {
        }

    }

}
