package team.study.common.base.aop;

import cn.hutool.core.date.StopWatch;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

    @Around(value = "repositoryPointCut() && @annotation(repositoryLog)")
    public Object doRepositoryAround(ProceedingJoinPoint joinPoint, RepositoryLog repositoryLog) throws Throwable {
        // 获取 @WebLog 注解的描述信息
        String methodDescription = repositoryLog.description();
        // 打印请求相关参数
        log.info("========================================== RepositoryLog Start ==========================================");
        // 打印描述信息
        log.info(String.format("%-15s\t : %-15s\t", "描述信息", methodDescription));
        // 打印调用 controller 的全路径以及执行方法
        log.info(String.format("%-15s\t : %-15s\t", "请求方法名", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()));
        // 打印请求入参
        log.info(String.format("%-15s\t : \n%s", "请求参数", JSONObject.toJSONString(joinPoint.getArgs(), SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat)));
        return calculateTime(joinPoint, "RepositoryLog");
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

    @Around("webPointCut() && @annotation(webLog)")
    public Object doWebAround(ProceedingJoinPoint joinPoint, WebLog webLog) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 获取 @WebLog 注解的描述信息
        String methodDescription = webLog.description();
        // 打印请求相关参数
        log.info("========================================== WebLog Start ==========================================");
        // 打印请求 url
        log.info(String.format("%-15s\t : %-15s\t", "URL路径", request.getRequestURL().toString()));
        // 打印描述信息
        log.info(String.format("%-15s\t : %-15s\t", "描述信息", methodDescription));
        // 打印 Http method
        log.info(String.format("%-15s\t : %-15s\t", "请求方式", request.getMethod()));
        // 打印调用 controller 的全路径以及执行方法
        log.info(String.format("%-15s\t : %-15s\t", "请求方法名", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()));
        // 打印请求的 IP
        String localhost = "0:0:0:0:0:0:0:1";
        String local = "本机";
        log.info(String.format("%-15s\t : %-15s\t", "IP地址", localhost.equals(request.getRemoteAddr()) ? local : request.getRemoteAddr()));
        // 打印请求入参
        log.info(String.format("%-15s\t : \n%s", "请求参数", JSONObject.toJSONString(joinPoint.getArgs(), SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat)));
        return calculateTime(joinPoint, "WebLog");
    }

    //endregion

    public Object calculateTime(ProceedingJoinPoint joinPoint, String logType) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();
        Object result = joinPoint.proceed();
        // 打印出参
        log.info(String.format("%-15s\t : \n%s", "执行结果为", JSONObject.toJSONString(result, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat)));
        sw.stop();
        // 执行耗时
        log.info("总共耗时 : {} ms", sw.getLastTaskTimeMillis());
        // 接口结束后换行，方便分割查看
        log.info("=========================================== " + logType + " End ===========================================" + LINE_SEPARATOR);
        return result;
    }
}
