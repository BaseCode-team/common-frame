package team.study.common.service.event.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.study.common.base.model.event.BaseDomainEvent;

/**
 * 领域事件示例
 *
 * @author baiyan
 * @date 2021/02/21
 */
@NoArgsConstructor
@Getter
public class DemoEvent extends BaseDomainEvent<String> {

    private String demoField;

    public DemoEvent(String data, String demoField) {
        super(data);
        this.demoField = demoField;
    }

}