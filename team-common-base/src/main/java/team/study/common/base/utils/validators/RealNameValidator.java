package team.study.common.base.utils.validators;

import cn.hutool.core.util.ReUtil;
import team.study.common.base.annotations.realname;
import team.study.common.base.constant.RegexpConstant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 真实姓名校验器
 *
 * @author JiaHao
 * @date 2022-11-26 22:02
 */
public class RealNameValidator implements ConstraintValidator<realname, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return ReUtil.isMatch(RegexpConstant.REGEXP_REAL_NAME, s);
    }
}
