package team.study.common.rpc.config;

/**
 * 异常包装
 * 开启hystrix，调用接口触发异常时，会触发熔断。
 * 但是对于HystrixBadRequestException是忽略的，对于业务类异常时不需要触发熔断。
 * 所以使用HystrixBadRequestException对业务类异常进行包装，跳过熔断。
 *
 * @author JiaHao
 * @date 2022/11/20 18:37
 **/
public interface ExceptionWrapper {

    /**
     * 异常包装
     *
     * @param wrapped
     * @return
     */
    Exception wrap(Exception wrapped);

}
