package team.study.common.service.event;


import team.study.common.base.model.event.BaseDomainEvent;

/**
 * 领域事件发布接口
 *
 * @author JiaHao
 * @date 2022/11/20 18:47
 **/
public interface DomainEventPublisher {

    /**
     * 发布事件
     *
     * @param event 领域事件
     */
    void publishEvent(BaseDomainEvent event);

}
