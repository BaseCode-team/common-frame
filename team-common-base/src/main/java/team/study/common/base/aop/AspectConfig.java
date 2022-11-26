package team.study.common.base.aop;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Maps;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

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
        StopWatch sw = new StopWatch();
        sw.start();
        Object result = joinPoint.proceed();
        sw.stop();
        final Log l = Log.builder()
                .threadId(Long.toString(Thread.currentThread().getId()))
                .threadName(Thread.currentThread().getName())
                .description(repositoryLog.description())
                .classMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName()))
                .requestParams(getNameAndValue(joinPoint)).build();
        log.info(String.format("%-15s\t : \n%s", "仓储请求日志信息", JSONObject.toJSONString(l, SerializerFeature.PrettyFormat, SerializerFeature.WriteDateUseDateFormat)));
        return result;
    }

    // endregion

    // region WebLog

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
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        // 打印请求相关参数
        StopWatch sw = new StopWatch();
        sw.start();
        Object result = joinPoint.proceed();
        sw.stop();
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);

        final Log l = Log.builder()
                .threadId(Long.toString(Thread.currentThread().getId()))
                .threadName(Thread.currentThread().getName())
                .description(webLog.description())
                .ip(getIp(request))
                .url(request.getRequestURL().toString())
                .classMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName()))
                .httpMethod(request.getMethod())
                .requestParams(getNameAndValue(joinPoint))
                .result(result)
                .timeCost(sw.getLastTaskTimeMillis())
                .userAgent(header)
                .browser(userAgent.getBrowser().toString())
                .os(userAgent.getOperatingSystem().toString()).build();

        log.info(String.format("%-15s\t : \n%s", "控制器请求日志信息", JSONObject.toJSONString(l, SerializerFeature.PrettyFormat, SerializerFeature.WriteDateUseDateFormat)));
        return result;
    }

    //endregion

    /**
     * 获取方法参数名和参数值
     *
     * @param joinPoint 切入点
     * @return Map<String, Object>
     */
    private Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {

        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = joinPoint.getArgs();

        if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
            return Collections.emptyMap();
        }
        if (names.length != args.length) {
            log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
            return Collections.emptyMap();
        }
        Map<String, Object> map = Maps.newHashMap();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        return map;
    }

    private static final String UNKNOWN = "unknown";

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        String local = "0:0:0:0:0:0:0:1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip) || local.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }
}
