package team.study.common.base.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;

/**
 * 单数据返回实体
 *
 * @author JiaHao
 * @date 2022-11-22 14:27
 */
public class SingleResponse<T> extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "数据", name = "data", dataType = "T")
    private T data;

    public SingleResponse() {
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static SingleResponse buildSuccess() {
        SingleResponse response = new SingleResponse();
        response.setSuccess(true);
        return response;
    }

    public static SingleResponse buildFailure(String errCode, String errMessage) {
        SingleResponse response = new SingleResponse();
        response.setSuccess(false);
        response.setCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }
}
