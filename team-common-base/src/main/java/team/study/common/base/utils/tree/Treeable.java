package team.study.common.base.utils.tree;

import java.util.List;


/**
 * Treeable
 *
 * @author JiaHao
 * @date 2022/11/20 17:31
 **/
public interface Treeable<T extends Treeable> {

    Object key();

    Object parentKey();

    T children(List<T> children);

    List<T> children();

    void add(T child);
}
