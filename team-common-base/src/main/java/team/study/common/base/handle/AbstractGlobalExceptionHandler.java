package team.study.common.base.handle;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import team.study.common.base.enums.ExceptionCode;
import team.study.common.base.exception.AuthException;
import team.study.common.base.exception.BizException;
import team.study.common.base.exception.ValidationException;
import team.study.common.base.response.Response;
import team.study.common.base.utils.StrPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static team.study.common.base.enums.ExceptionCode.METHOD_NOT_ALLOWED;
import static team.study.common.base.enums.ExceptionCode.REQUIRED_FILE_PARAM_EX;

/**
 * 全局默认异常处理
 * 业务应用通过继承此类的方式进行异常拦截处理
 *
 * @author JiaHao
 * @date 2022/11/20 18:35
 **/
@Slf4j
public abstract class AbstractGlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handle(BindException ex) {
        log.warn("BindException:", ex);
        try {
            String msg = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
            if (StrUtil.isNotEmpty(msg)) {
                return Response.buildFailure(ExceptionCode.PARAM_EX.getCode(), ExceptionCode.PARAM_EX.getDesc(), msg).setPath(getPath());
            }
        } catch (Exception ee) {
            log.debug("获取异常描述失败", ee);
        }
        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        fieldErrors.forEach((oe) ->
                msg.append("参数:[").append(oe.getObjectName())
                        .append(".").append(oe.getField())
                        .append("]的传入值:[").append(oe.getRejectedValue()).append("]与预期的字段类型不匹配.")
        );
        return Response.buildFailure(ExceptionCode.PARAM_EX.getCode(), ExceptionCode.PARAM_EX.getDesc(), msg.toString()).setPath(getPath());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.warn("MethodArgumentTypeMismatchException:", ex);
        String msg = "参数：[" + ex.getName() + "]的传入值：[" + ex.getValue() +
                "]与预期的字段类型：[" + Objects.requireNonNull(ex.getRequiredType()).getName() + "]不匹配";
        return Response.buildFailure(ExceptionCode.PARAM_EX.getCode(), ExceptionCode.PARAM_EX.getDesc(), msg).setPath(getPath());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response illegalStateException(IllegalStateException ex) {
        log.warn("IllegalStateException:", ex);
        return Response.buildFailure(ExceptionCode.ILLEGAL_ARGUMENT_EX.getCode(), ExceptionCode.ILLEGAL_ARGUMENT_EX.getDesc(), ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.warn("MissingServletRequestParameterException:", ex);
        return Response.buildFailure(ExceptionCode.ILLEGAL_ARGUMENT_EX.getCode(), "缺少必须的[" + ex.getParameterType() + "]类型的参数[" + ex.getParameterName() + "]", ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response nullPointerException(NullPointerException ex) {
        log.warn("NullPointerException:", ex);
        return Response.buildFailure(ExceptionCode.NULL_POINT_EX.getCode(), ExceptionCode.NULL_POINT_EX.getDesc(), ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response illegalArgumentException(IllegalArgumentException ex) {
        log.warn("IllegalArgumentException:", ex);
        return Response.buildFailure(ExceptionCode.ILLEGAL_ARGUMENT_EX.getCode(), ExceptionCode.ILLEGAL_ARGUMENT_EX.getDesc(), ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.warn("HttpMediaTypeNotSupportedException:", ex);
        MediaType contentType = ex.getContentType();
        if (contentType != null) {
            return Response.buildFailure(ExceptionCode.MEDIA_TYPE_EX.getCode(), "请求类型(Content-Type)[" + contentType + "] 与实际接口的请求类型不匹配", ex.getMessage()).setPath(getPath());
        }
        return Response.buildFailure(ExceptionCode.MEDIA_TYPE_EX.getCode(), "无效的Content-Type类型", ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response missingServletRequestPartException(MissingServletRequestPartException ex) {
        log.warn("MissingServletRequestPartException:", ex);
        return Response.buildFailure(REQUIRED_FILE_PARAM_EX.getCode(), REQUIRED_FILE_PARAM_EX.getDesc(), ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(ServletException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response servletException(ServletException ex) {
        log.warn("ServletException:", ex);
        String msg = "UT010016: Not a multi part request";
        if (msg.equalsIgnoreCase(ex.getMessage())) {
            return Response.buildFailure(REQUIRED_FILE_PARAM_EX.getCode(), REQUIRED_FILE_PARAM_EX.getDesc(), ex.getMessage());
        }
        return Response.buildFailure(ExceptionCode.SYSTEM_BUSY.getCode(), ex.getMessage(), ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response multipartException(MultipartException ex) {
        log.warn("MultipartException:", ex);
        return Response.buildFailure(REQUIRED_FILE_PARAM_EX.getCode(), REQUIRED_FILE_PARAM_EX.getDesc(), ex.getMessage()).setPath(getPath());
    }

    /**
     * jsr 规范中的验证异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response constraintViolationException(ConstraintViolationException ex) {
        log.warn("ConstraintViolationException:", ex);
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));

        return Response.buildFailure(ExceptionCode.BASE_VALID_PARAM.getCode(), message, ex.getMessage()).setPath(getPath());
    }

    /**
     * spring 封装的参数验证异常， 在controller中没有写result参数时，会进入
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn("MethodArgumentNotValidException:", ex);
        return Response.buildFailure(ExceptionCode.BASE_VALID_PARAM.getCode(), Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage(), ex.getMessage()).setPath(getPath());
    }

    private String getPath() {
        String path = StrPool.EMPTY;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            path = request.getRequestURI();
        }
        return path;
    }

    /**
     * 其他异常
     *
     * @param ex 异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response otherExceptionHandler(Exception ex) {
        log.warn("Exception:", ex);
        if (ex.getCause() instanceof BizException) {
            return Response.buildFailure((BizException) ex.getCause());
        }
        return Response.buildFailure(ExceptionCode.SYSTEM_BUSY.getCode(), ExceptionCode.SYSTEM_BUSY.getDesc(), ex.getMessage()).setPath(getPath());
    }


    /**
     * 返回状态码:405
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.warn("HttpRequestMethodNotSupportedException:", ex);
        return Response.buildFailure(METHOD_NOT_ALLOWED.getCode(), METHOD_NOT_ALLOWED.getDesc(), ex.getMessage()).setPath(getPath());
    }


    @ExceptionHandler(PersistenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response persistenceException(PersistenceException ex) {
        log.warn("PersistenceException:", ex);
        if (ex.getCause() instanceof BizException) {
            BizException cause = (BizException) ex.getCause();
            return Response.buildFailure(cause.getErrCode(), null, cause.getMessage());
        }
        return Response.buildFailure(ExceptionCode.SQL_EX.getCode(), ExceptionCode.SQL_EX.getDesc(), ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(MyBatisSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response myBatisSystemException(MyBatisSystemException ex) {
        log.warn("PersistenceException:", ex);
        if (ex.getCause() instanceof PersistenceException) {
            return this.persistenceException((PersistenceException) ex.getCause());
        }
        return Response.buildFailure(ExceptionCode.SQL_EX.getCode(), ExceptionCode.SQL_EX.getDesc(), ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response sqlException(SQLException ex) {
        log.warn("SQLException:", ex);
        return Response.buildFailure(ExceptionCode.SQL_EX.getCode(), ExceptionCode.SQL_EX.getDesc(), ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response dataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.warn("DataIntegrityViolationException:", ex);
        return Response.buildFailure(ExceptionCode.SQL_EX.getCode(), ExceptionCode.SQL_EX.getDesc(), ex.getMessage()).setPath(getPath());
    }


    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handle(ValidationException ex) {
        log.error("业务参数校验异常", ex);
        return Response.buildFailure(ExceptionCode.BASE_VALID_PARAM.getCode(), ExceptionCode.BASE_VALID_PARAM.getDesc(), ex.getMessage());
    }

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handle(BizException ex) {
        log.error("业务异常", ex);
        return Response.buildFailure(ExceptionCode.SYSTEM_BUSY.getCode(), ExceptionCode.SYSTEM_BUSY.getDesc(), ex.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handle(Throwable ex) {
        log.error("全局异常", ex);
        return Response.buildFailure(ExceptionCode.SYSTEM_BUSY.getCode(), ExceptionCode.SYSTEM_BUSY.getDesc(), ex.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handle(AuthException ex) {
        log.error("鉴权异常", ex);
        return Response.buildFailure(ex);
    }
}
