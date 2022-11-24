package team.study.common.base.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import team.study.common.base.exception.BaseException;
import team.study.common.base.model.dto.DTO;

import java.io.Serial;

/**
 * 返回实体
 *
 * @author JiaHao
 * @date 2022-11-22 14:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Response extends DTO {
    @Serial
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "是否成功", name = "success", dataType = "boolean")
    private boolean success;
    @ApiModelProperty(value = "错误编码", name = "errCode", dataType = "String")
    private String errCode;
    @ApiModelProperty(value = "错误信息", name = "errMessage", dataType = "String")
    private String errMessage;

    /**
     * 链路id
     */
    @ApiModelProperty(value = "链路ID", name = "traceId", dataType = "String", notes = "暂时用不上")
    private String traceId;


    public Response() {
    }


    @Override
    public String toString() {
        return "Response [success=" + this.success + ", errCode=" + this.errCode + ", errMessage=" + this.errMessage + "]";
    }

    public static Response buildSuccess() {
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }


    public static Response buildFailure(String errCode, String errMessage) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static Response buildFailure(BaseException ex) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(ex.getErrCode());
        response.setErrMessage(ex.getMessage());
        return response;
    }

    /**
     * 通用业务请求状态码
     */
    public static final Integer CODE_SUCCESS = 200;
    public static final Integer CODE_SYSTEM_ERROR = 500;
    public static final Integer CODE_AUTH_ERROR = 401;

    /**
     * 通用请求信息
     */
    public static final String SYSTEM_ERROR = "系统错误";
    public static final String SYSTEM_AUTH_ERROR = "用户鉴权失败";
    public static final String MESSAGE_SUCCESS = "请求成功";
    public static final String QUERY_SUCCESS = "查询成功";
    public static final String INSERT_SUCCESS = "新增成功";
    public static final String UPDATE_SUCCESS = "更新成功";
    public static final String DELETE_SUCCESS = "删除成功";
    public static final String IMPORT_SUCCESS = "导入成功";
    public static final String EXPORT_SUCCESS = "导出成功";
    public static final String DOWNLOAD_SUCCESS = "下载成功";
}