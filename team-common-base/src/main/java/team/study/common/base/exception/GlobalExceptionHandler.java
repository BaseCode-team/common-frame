package team.study.common.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import team.study.common.base.enums.ErrorCodeEnum;
import team.study.common.base.response.Response;
import team.study.common.base.utils.StringUtil;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 全局默认异常处理
 * 业务应用通过继承此类的方式进行异常拦截处理
 *
 * @author JiaHao
 * @date 2022/11/20 18:35
 **/
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public Response handle(ConstraintViolationException ex) {
        log.error("入参校验异常", ex);
        StringBuilder errorCode = new StringBuilder();
        StringBuilder errorMessage = new StringBuilder();
        ex.getConstraintViolations()
                .forEach(error -> {
                    if (StringUtil.isNotBlank(errorCode.toString())) {
                        errorCode.append(",");
                    }
                    errorCode.append(error.getMessageTemplate());
                    if (StringUtil.isNotBlank(errorMessage.toString())) {
                        errorMessage.append(",");
                    }
                    errorMessage.append(error.getMessage());
                });
        return Response.buildFailure(errorCode.toString(), errorMessage.toString());
    }

    @ExceptionHandler(BindException.class)
    public Response handle(BindException ex) {
        log.error("方法参数校验异常", ex);
        StringBuilder errorCode = new StringBuilder();
        StringBuilder errorMessage = new StringBuilder();
        BindingResult result = ex.getBindingResult();
        if (result.hasErrors()) {
            errorCode.append(ErrorCodeEnum.VALID_ERROR.getCode());
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                if (errorMessage.length() > 0) {
                    errorMessage.append(",");
                }
                errorMessage.append(String.format("%s中的%s %s", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage()));
            });
        }
        return Response.buildFailure(errorCode.toString(), errorMessage.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handle(MethodArgumentNotValidException ex) {
        log.error("方法参数校验异常", ex);
        StringBuilder errorCode = new StringBuilder();
        StringBuilder errorMessage = new StringBuilder();
        BindingResult result = ex.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errorCode.append(ErrorCodeEnum.VALID_ERROR.getCode());
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                if (errorMessage.length() > 0) {
                    errorMessage.append(",");
                }
                errorMessage.append(fieldError.getDefaultMessage());
            });
        }
        return Response.buildFailure(errorCode.toString(), errorMessage.toString());
    }

    @ExceptionHandler(BizException.class)
    public Response handle(ValidationException ex) {
        log.error("业务参数校验异常", ex);
        return Response.buildFailure(ex);
    }

    @ExceptionHandler(Throwable.class)
    public Response handle(Throwable ex) {
        log.error("全局异常", ex);
        return Response.buildFailure("1000", Response.SYSTEM_ERROR);
    }

    @ExceptionHandler(AuthException.class)
    public Response handle(AuthException ex) {
        log.error("鉴权异常", ex);
        return Response.buildFailure(ex);
    }

    /**
     * 获取国际化数据
     *
     * @param messageTemplate 消息模板
     * @return
     */
    private String getFromMessageTemplate(String messageTemplate) {
        if (StringUtil.isBlank(messageTemplate)) {
            return null;
        }
        if (messageTemplate.length() < 2) {
            return null;
        }
        return messageTemplate.substring(1, messageTemplate.length() - 1);
    }

    /**
     * 构建并绑定返回结果
     *
     * @param errorCode     错误code
     * @param errorMessage  国际化错误信息
     * @param bindingResult 需要处理的错误信息
     */
//    private void buildBindingResult(StringBuilder errorCode, StringBuilder errorMessage, BindingResult bindingResult) {
//        List<ObjectError> errors = bindingResult.getAllErrors();
//        errors
//                .forEach(error -> {
//                    if (error.contains(ConstraintViolation.class)) {
//                        ConstraintViolation constraintViolation = error.unwrap(ConstraintViolation.class);
//                        if (errorCode.length() > 0) {
//                            errorCode.append(",");
//                        }
//                        errorCode.append(getFromMessageTemplate(constraintViolation.getMessageTemplate()));
//                    }
//                    if (errorMessage.length() > 0) {
//                        errorMessage.append(",");
//                    }
//                    String errorInfo = messageSource.getMessage(getFromMessageTemplate(error.getDefaultMessage()), null, (Object) null);
//                    errorMessage.append(errorInfo);
//                });
//    }


}
