package team.study.common.base.exception;

import lombok.EqualsAndHashCode;
import team.study.common.base.enums.ErrorCodeEnum;

import java.io.Serial;

/**
 * 线程池任务异常
 *
 * @author JiaHao
 * @date 2022/11/20 17:15
 **/

@EqualsAndHashCode(callSuper = true)
public class ThreadPoolExecutorException extends BaseException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用表示异常原因的对象构造新实例。
     *
     * @param cause 异常原因。
     */
    public ThreadPoolExecutorException(Throwable cause) {
        super(ErrorCodeEnum.THREAD_POOL_ERROR.getCode(), cause);
    }

}
