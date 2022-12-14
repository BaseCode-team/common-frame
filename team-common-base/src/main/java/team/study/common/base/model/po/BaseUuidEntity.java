package team.study.common.base.model.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * po层实体
 * 逻辑删除
 *
 * @author JiaHao
 * @date 2022/11/20 17:24
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseUuidEntity extends Entity {

    /**
     * 是否删除，0位未删除
     */
    @TableLogic(delval = "current_timestamp()")
    private Long deleted;

}
