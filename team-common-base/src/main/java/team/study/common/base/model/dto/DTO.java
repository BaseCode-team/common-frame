package team.study.common.base.model.dto;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据传输基类
 *
 * @author JiaHao
 * @date 2022-11-22 14:21
 */
public abstract class DTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public DTO() {
    }
}
