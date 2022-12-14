package team.study.common.base.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 树形实体类
 *
 * @author JiaHao
 * @date 2022-12-13 21:47
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TreeEntity<E extends Serializable> extends Entity {

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 255, message = "名称长度不能超过255")
    @TableField(value = "label")
    protected String label;

    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
    @TableField(value = "parent_id")
    protected Long parentId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序号")
    @TableField(value = "sort_value")
    protected Integer sortValue;


    @ApiModelProperty(value = "子节点", hidden = true)
    @TableField(exist = false)
    protected List<E> children;


    /**
     * 初始化子类
     */
    @JsonIgnore
    public void initChildren() {
        if (getChildren() == null) {
            this.setChildren(new ArrayList<>());
        }
    }

    @JsonIgnore
    public void addChildren(E child) {
        initChildren();
        children.add(child);
    }
}

