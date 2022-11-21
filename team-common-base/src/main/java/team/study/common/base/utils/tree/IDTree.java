package team.study.common.base.utils.tree;


/**
 * IDTree
 *
 * @author JiaHao
 * @date 2022/11/20 17:30
 **/
public abstract class IDTree<T extends Treeable> extends AbstractTree<T> {
    @Override
    public Object key() {
        return getId();
    }

    @Override
    public Object parentKey() {
        return getParentId();
    }

    /**
     * 获取当前ID
     *
     * @return Long
     */
    public abstract Long getId();

    /**
     * 获取parent ID
     *
     * @return Long
     */
    public abstract Long getParentId();

}
