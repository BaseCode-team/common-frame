package team.study.common.base.event;

import org.springframework.context.ApplicationEvent;
import team.study.common.base.model.system.OptLogDTO;

/**
 * 系统日志事件
 *
 * @author JiaHao
 * @date 2022-12-13 14:01
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(OptLogDTO source) {
        super(source);
    }
}
