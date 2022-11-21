package team.study.common.base.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础字典类
 *
 * @author JiaHao
 * @date 2022/11/20 17:23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataDictDTO {

    @ApiModelProperty("key值")
    private String key;

    @ApiModelProperty("value值")
    private String value;

}
