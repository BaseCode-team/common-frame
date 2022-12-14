package team.study.common.base.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 多数据返回实体
 *
 * @author JiaHao
 * @date 2022-11-22 14:28
 */
public class MultiResponse<T> extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "数据", name = "data", dataType = "T")
    private Collection<T> data;

    public MultiResponse() {
    }

    public List<T> getData() {
        if (null == this.data) {
            return Collections.emptyList();
        } else {
            return (List) (this.data instanceof List ? (List) this.data : new ArrayList(this.data));
        }
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public boolean isEmpty() {
        return this.data == null || this.data.isEmpty();
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    public static MultiResponse buildSuccess() {
        MultiResponse response = new MultiResponse();
        response.setSuccess(true);
        return response;
    }

    public static MultiResponse buildFailure(String errCode, String errMessage) {
        MultiResponse response = new MultiResponse();
        response.setSuccess(false);
        response.setCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static <T> MultiResponse<T> of(Collection<T> data) {
        MultiResponse<T> response = new MultiResponse();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }
}