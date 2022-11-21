package team.study.common.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 线程池任务异常
 *
 * @author JiaHao
 * @date 2022/11/20 17:15
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ThreadPoolExecutorException extends ServiceException {

    /**
     * 用表示异常原因的对象构造新实例。
     *
     * @param cause 异常原因。
     */
    public ThreadPoolExecutorException(Throwable cause) {
        super(null, cause);
    }

}
