package team.study.common.rpc.config.hystrix;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import team.study.common.rpc.config.ExceptionWrapper;

/**
 * @author JiaHao
 */
public class HystrixExceptionWrapper implements ExceptionWrapper {
    @Override
    public Exception wrap(Exception wrapped) {
        return new HystrixBadRequestException(wrapped.getMessage(), wrapped);
    }
}
