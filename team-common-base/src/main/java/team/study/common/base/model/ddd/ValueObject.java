package team.study.common.base.model.ddd;


/**
 * 值对象标记接口
 *
 * @author JiaHao
 * @date 2022/11/20 17:22
 **/
public interface ValueObject<T> extends MarkerInterface {

    /**
     * 值对象通过属性比较
     *
     * @param other 比较对象
     * @return 是否相同
     */
    boolean sameValueAs(T other);

}
