package team.study.common.base.model.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 关键字查询条件
 *
 * @author JiaHao
 * @date 2022/11/20 17:25
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class KeywordQuery extends PageQuery {

    @ApiModelProperty(value = "关键字查询", name = "keyword")
    private String keyword;
}
