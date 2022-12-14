package team.study.common.base.model.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类
 *
 * @author JiaHao
 * @date 2022-12-13 14:44
 */
@Data
public class Entity implements Serializable {
    public static final String UPDATE_TIME = "updateTime";
    public static final String UPDATE_BY = "updateBy";
    public static final String UPDATE_TIME_COLUMN = "update_time";
    public static final String UPDATE_BY_COLUMN = "update_by";
    public static final String FIELD_ID = "id";
    public static final String CREATE_TIME = "createTime";
    public static final String CREATE_TIME_COLUMN = "create_time";
    public static final String CREATE_BY = "createBy";
    public static final String CREATE_BY_COLUMN = "create_by";
    public static final String CREATED_ORG_ID = "orgId";
    public static final String CREATED_ORG_ID_FIELD = "org_id";

    /**
     * 主键id 采用默认雪花算法
     */
    @TableId
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "最后修改人ID")
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    protected Long updateBy;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    protected Long createBy;
}
