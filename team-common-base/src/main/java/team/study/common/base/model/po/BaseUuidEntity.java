package team.study.common.base.model.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * po层实体
 * 逻辑删除
 *
 * @author JiaHao
 * @date 2022/11/20 17:24
 **/
@Data
public class BaseUuidEntity {

    /**
     * 主键id 采用默认雪花算法
     */
    @TableId
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除，0位未删除
     */
    @TableLogic(delval = "current_timestamp()")
    private Long deleted;

}
