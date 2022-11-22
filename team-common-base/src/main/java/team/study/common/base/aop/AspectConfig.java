package team.study.common.base.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller AOP切面工具
 *
 * @author JiaHao
 * @version 1.0
 * @date 2022-11-17 12:15
 * @description AOP切面工具
 */
@Aspect
@Component
@Slf4j
public class AspectConfig {
    // region 仓储Log

    /**
     * 切点为RepositoryLog注解
     *
     * @author JiaHao
     * @date 2022/11/22 12:46
     **/
    @Pointcut("@annotation(team.study.common.base.aop.RepositoryLog)")
    public void repositoryPointCut() {
    }

    @Before(value = "repositoryPointCut() && @annotation(repositoryLog)")
    public void doRepositoryBefore(JoinPoint joinPoint, RepositoryLog repositoryLog) throws Exception {
        // 获取 @WebLog 注解的描述信息
        String methodDescription = repositoryLog.description();
        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印描述信息
        log.info("Description    : {}", methodDescription);
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求入参
        log.info("Request Args   : {}", JSONObject.toJSON(joinPoint.getArgs()));
    }

    @Around("repositoryPointCut()")
    public Object doRepositoryAround(ProceedingJoinPoint jointPoint) throws Throwable {
        return doAround(jointPoint);
    }

    @After(value = "repositoryPointCut()")
    public void doRepositoryAfter(JoinPoint joinPoint) {
        doAfter(joinPoint);
    }
    // endregion

    // region WebLog
    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Service层切点
     *
     * @date 2022/11/17 12:17
     **/
    @Pointcut("@annotation(team.study.common.base.aop.WebLog)")
    public void webPointCut() {
    }

    /**
     * 切点方法执行前运行
     *
     * @date 2022/11/17 12:17
     **/
    @Before(value = "webPointCut() && @annotation(webLog)")
    public void doWebBefore(JoinPoint joinPoint, WebLog webLog) throws Exception {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 获取 @WebLog 注解的描述信息
        String methodDescription = webLog.description();
        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印描述信息
        log.info("Description    : {}", methodDescription);
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args   : {}", JSONObject.toJSON(joinPoint.getArgs()));
    }

    @Around("webPointCut()")
    public Object doWebAround(ProceedingJoinPoint jointPoint) throws Throwable {
        return doAround(jointPoint);
    }

    /**
     * 切点方法执行后运行，不管切点方法执行成功还是出现异常
     */
    @After(value = "webPointCut()")
    public void doWebAfter(JoinPoint joinPoint) {
        doAfter(joinPoint);
    }
    //endregion

    public void doAfter(JoinPoint joinPoint) {
        // 接口结束后换行，方便分割查看
        log.info("=========================================== End ===========================================" + LINE_SEPARATOR);
    }

    public Object doAround(ProceedingJoinPoint jointPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = jointPoint.proceed();
        // 打印出参
        log.info("Response Args  : {}", JSONObject.toJSON(result));
        // 执行耗时
        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

}
