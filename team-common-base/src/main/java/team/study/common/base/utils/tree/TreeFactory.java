package team.study.common.base.utils.tree;

/**
 * TreeFactory
 *
 * @author JiaHao
 * @date 2022/11/20 17:31
 **/
public interface TreeFactory<I, O extends Treeable> {

    O convert(I i);

    O buildDefaultRoot();

}
