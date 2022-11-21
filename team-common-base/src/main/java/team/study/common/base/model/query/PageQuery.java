package team.study.common.base.model.query;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import team.study.common.base.result.Page;
import team.study.common.base.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页查询
 *
 * @author JiaHao
 * @date 2022/11/20 17:25
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PageQuery extends Page {

    private Long offset = -1L;

    private Long limit = 20L;

    private final String ASC = "asc";
    private final String DESC = "desc";

    private final String ID = "id";

    private String sort;

    private String order;

    protected final Map<String, String> SORTS_MAP = new HashMap<>();

    protected final Map<String, String> ORDER_MAP = new HashMap<>();

    protected final Map<String, String> REVERSAL_ORDER_MAP = new HashMap<>();

    {
        SORTS_MAP.put(ASC, ASC);
        SORTS_MAP.put(DESC, DESC);
    }

    public Long getOffset() {
        return offset;
    }

    @Override
    public void setOffset(Long offset) {
        this.offset = offset;
        super.setOffset(offset);
    }

    public Long getLimit() {
        return getSize();
    }

    @Override
    public void setLimit(Long limit) {
        this.limit = limit;
        setSize(limit);
    }

    public Long getPageNo() {
        return getCurrent();
    }

    public void setPageNo(Long pageNum) {
        setCurrent(pageNum);
    }

    public Long getPageSize() {
        return getSize();
    }

    public void setPageSize(Long pageSize) {
        setSize(pageSize);
    }

    @Override
    public long offset() {
        if (getOffset() >= 0) {
            return getOffset();
        }
        return getCurrent() > 0 ? (getCurrent() - 1) * getSize() : 0;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public List getRecords() {
        return super.getRecords();
    }

    @Override
    @ApiModelProperty(hidden = true)
    public long getSize() {
        return super.getSize();
    }

    @Override
    @ApiModelProperty(hidden = true)
    public long getTotal() {
        return super.getTotal();
    }

    public String getOrder() {
        String order = ORDER_MAP.get(this.order);
        if (StringUtil.isNotBlank(order)) {
            return order;
        } else if (ORDER_MAP.containsValue(this.order)) {
            return this.order;
        }

        order = REVERSAL_ORDER_MAP.get(this.order);
        if (StringUtil.isNotBlank(order)) {
            reversalSort();
            return order;
        }

        return null;
    }

    public String getSort() {
        String sort = SORTS_MAP.get(this.sort);
        return StringUtil.isBlank(sort) ? DESC : sort;
    }

    private void reversalSort() {
        String sort = getSort();
        if (ASC.equals(sort)) {
            setSort(DESC);
        } else {
            setSort(ASC);
        }
    }

    public void addOrderItem() {
        List<OrderItem> orderItems = new ArrayList<>();
        if (StringUtil.isNotBlank(this.getOrder())) {
            orderItems.add(this.getSort().equals(ASC) ? OrderItem.asc(this.getOrder()) : OrderItem.desc(this.getOrder()));
        } else {
            orderItems.add(OrderItem.desc(ID));
        }
        this.addOrder(orderItems);
    }
}

