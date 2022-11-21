package team.study.common.rpc.config;

/**
 * 默认异常处理
 *
 * @author JiaHao
 * @date 2022/11/20 18:37
 **/
public class DefaultExceptionWrapper implements ExceptionWrapper {
    @Override
    public Exception wrap(Exception wrapped) {
        return wrapped;
    }
}
