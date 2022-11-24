package team.study.common.base.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 分页返回实体
 *
 * @author JiaHao
 * @date 2022-11-22 14:26
 */
public class PageResponse<T> extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "总数", name = "totalCount", dataType = "int")
    private int totalCount = 0;
    @ApiModelProperty(value = "总页数", name = "pageSize", dataType = "int")
    private int pageSize = 1;
    @ApiModelProperty(value = "当前页数", name = "pageIndex", dataType = "int")
    private int pageIndex = 1;
    @ApiModelProperty(value = "数据", name = "data", dataType = "Collection<T>")
    private Collection<T> data;

    public PageResponse() {
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return this.pageSize < 1 ? 1 : this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }

    }

    public int getPageIndex() {
        return this.pageIndex < 1 ? 1 : this.pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        if (pageIndex < 1) {
            this.pageIndex = 1;
        } else {
            this.pageIndex = pageIndex;
        }

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

    public int getTotalPages() {
        return this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
    }

    public boolean isEmpty() {
        return this.data == null || this.data.isEmpty();
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    public static PageResponse buildSuccess() {
        PageResponse response = new PageResponse();
        response.setSuccess(true);
        return response;
    }

    public static PageResponse buildFailure(String errCode, String errMessage) {
        PageResponse response = new PageResponse();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static <T> PageResponse<T> of(int pageSize, int pageIndex) {
        PageResponse<T> response = new PageResponse();
        response.setSuccess(true);
        response.setData(Collections.emptyList());
        response.setTotalCount(0);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }

    public static <T> PageResponse<T> of(Collection<T> data, int totalCount, int pageSize, int pageIndex) {
        PageResponse<T> response = new PageResponse();
        response.setSuccess(true);
        response.setData(data);
        response.setTotalCount(totalCount);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }
}