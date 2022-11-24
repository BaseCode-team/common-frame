package team.study.common.base.model.query;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分页查询
 *
 * @author JiaHao
 * @date 2022/11/20 17:25
 **/

public abstract class PageQuery extends Query {
    private static final long serialVersionUID = 1L;
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";
    private static final int DEFAULT_PAGE_SIZE = 10;
    @ApiModelProperty(value = "每页数据数量", name = "pageSize", dataType = "int")
    private int pageSize = 10;
    @ApiModelProperty(value = "当前页数", name = "pageIndex", dataType = "int")
    private int pageIndex = 1;
    @ApiModelProperty(value = "按哪一列排序", name = "orderBy", dataType = "String")
    private String orderBy;
    @ApiModelProperty(value = "顺序(ASC)还是逆序(DESC)", name = "orderDirection", dataType = "String", notes = "顺序是ASC，逆序是DESC")
    private String orderDirection = "DESC";
    @ApiModelProperty(value = "分组", name = "groupBy", dataType = "String")
    private String groupBy;
    @ApiModelProperty(value = "是否需要总数", name = "needTotalCount", dataType = "boolean")
    private boolean needTotalCount = true;

    public PageQuery() {
    }

    public int getPageIndex() {
        return this.pageIndex < 1 ? 1 : this.pageIndex;
    }

    public PageQuery setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public int getPageSize() {
        if (this.pageSize < 1) {
            this.pageSize = 10;
        }

        return this.pageSize;
    }

    public PageQuery setPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = 10;
        }

        this.pageSize = pageSize;
        return this;
    }

    public int getOffset() {
        return (this.getPageIndex() - 1) * this.getPageSize();
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public PageQuery setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public String getOrderDirection() {
        return this.orderDirection;
    }

    public PageQuery setOrderDirection(String orderDirection) {
        if (ASC.equalsIgnoreCase(orderDirection) || DESC.equalsIgnoreCase(orderDirection)) {
            this.orderDirection = orderDirection;
        }

        return this;
    }

    public String getGroupBy() {
        return this.groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public boolean isNeedTotalCount() {
        return this.needTotalCount;
    }

    public void setNeedTotalCount(boolean needTotalCount) {
        this.needTotalCount = needTotalCount;
    }
}

