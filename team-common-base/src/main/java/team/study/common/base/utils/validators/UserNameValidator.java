package team.study.common.base.utils.validators;

import cn.hutool.core.util.ReUtil;
import team.study.common.base.annotations.username;
import team.study.common.base.constant.RegexpConstant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 用户名校验器
 *
 * @author JiaHao
 * @date 2022-11-26 22:01
 */
public class UserNameValidator implements ConstraintValidator<username, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return ReUtil.isMatch(RegexpConstant.REGEXP_USERNAME, s);
    }
}
