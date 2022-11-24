package team.study.common.base.exception;

import javax.validation.groups.Default;

/**
 * @author JiaHao
 */
public interface ValidGroup extends Default {
    interface Create extends ValidGroup {
    }

    interface Update extends ValidGroup {
    }

    interface Query extends ValidGroup {
    }

    interface Delete extends ValidGroup {
    }
}