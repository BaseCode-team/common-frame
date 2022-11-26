package team.study.common.base.utils.validators;

import cn.hutool.core.util.ReUtil;
import team.study.common.base.annotations.phone;
import team.study.common.base.constant.RegexpConstant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 电话号码校验器
 *
 * @author JiaHao
 * @date 2022-11-26 21:46
 */
public class PhoneValidator implements ConstraintValidator<phone, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return ReUtil.isMatch(RegexpConstant.MOBILE_PHONE, s);
    }
}
