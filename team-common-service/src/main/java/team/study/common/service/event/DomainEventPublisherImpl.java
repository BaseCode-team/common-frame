package team.study.common.service.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import team.study.common.base.model.event.BaseDomainEvent;
import team.study.common.base.utils.JsonUtil;

/**
 * 领域事件发布实现类
 *
 * @author JiaHao
 * @date 2022/11/20 18:47
 **/
@Component
@Slf4j
@ConditionalOnProperty(value = "baiyan.config.domain.event.enable", havingValue = "true")
public class DomainEventPublisherImpl implements DomainEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public DomainEventPublisherImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publishEvent(BaseDomainEvent event) {
        log.debug("发布事件,event:{}", JsonUtil.jsonToString(event));
        applicationEventPublisher.publishEvent(event);
    }

}
