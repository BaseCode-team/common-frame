package team.study.common.base.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 领域事件基类
 *
 * @author JiaHao
 * @date 2022/11/20 17:23
 **/
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDomainEvent<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1465328245048581896L;

    /**
     * 领域事件数据
     */
    private T data;

    public BaseDomainEvent(T data) {
        this.data = data;
    }

}
