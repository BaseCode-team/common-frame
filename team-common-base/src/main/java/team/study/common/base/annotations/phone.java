package team.study.common.base.annotations;

import team.study.common.base.utils.validators.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author JiaHao
 */

@Target({ElementType.FIELD})  //作用于字段，枚举上
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class) //校验的逻辑处理类
public @interface phone {
    String message() default "手机号不符合校验规则";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
