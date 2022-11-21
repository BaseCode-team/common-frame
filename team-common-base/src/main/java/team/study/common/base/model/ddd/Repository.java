package team.study.common.base.model.ddd;

import java.io.Serializable;


/**
 * 基础仓储接口
 *
 * @author JiaHao
 * @date 2022/11/20 17:22
 **/
public interface Repository<AGGREGATE, ID extends Serializable> {

    /**
     * 删除
     *
     * @param id id
     */
    void delete(ID id);

    /**
     * 按id查找
     *
     * @param id id
     * @return AGGREGATE
     */
    AGGREGATE byId(ID id);

    /**
     * 保存或更新聚合根
     *
     * @param aggregate 聚合根
     * @param <S>       聚合根类型
     * @return 聚合根
     */
    <S extends AGGREGATE> S save(S aggregate);

    /**
     * 保存或更新聚合根【直接刷表】
     *
     * @param aggregate 聚合根
     * @param <S>       聚合根类型
     * @return 聚合根
     */
    default <S extends AGGREGATE> S saveAndFlush(S aggregate) {
        return aggregate;
    }

}
