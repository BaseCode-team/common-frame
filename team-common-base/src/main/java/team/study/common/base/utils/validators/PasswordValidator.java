package team.study.common.base.utils.validators;

import cn.hutool.core.util.ReUtil;
import team.study.common.base.annotations.password;
import team.study.common.base.constant.RegexpConstant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 密码校验器
 *
 * @author JiaHao
 * @date 2022-11-26 22:01
 */
public class PasswordValidator implements ConstraintValidator<password, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return ReUtil.isMatch(RegexpConstant.USER_PWD, s);
    }
}
