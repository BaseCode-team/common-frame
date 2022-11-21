package team.study.common.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * http请求工具类
 *
 * @author JiaHao
 * @date 2022/11/20 17:28
 **/
@Slf4j
public class HttpRequestUtil {

    private static final String UNKNOW = "unknow";
    private static final String UNKNOWN = "unknown";

    /**
     * 获取request
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert ra != null;
        return ra.getRequest();
    }

    /**
     * 获取response
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert ra != null;
        return ra.getResponse();
    }

    /**
     * 获取session
     *
     * @return HttpSession
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取客户端IP
     */
    public static String getIpAddr() {
        return getIpAddr(getRequest());
    }

    /**
     * 获取图片hashcode
     *
     * @return String
     */
    public static String getImageCodeHash() {
        return getRequest().getHeader("Hash");
    }


    /**
     * 获取客户端IP
     *
     * @param request request请求
     * @return String
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        ip = HttpRequestUtil.getIpAddrFromProxyClient(ip, request);
        ip = HttpRequestUtil.getIpAddrFromWLProxyClient(ip, request);
        ip = HttpRequestUtil.getIpAddrFromRemoteAddr(ip, request);
        return HttpRequestUtil.parseIp(ip);
    }

    /**
     * 解析ip
     *
     * @param ip IP字符串
     * @return String
     */
    private static String parseIp(String ip) {
        int ipLen = 15;
        if (StringUtil.isNotBlank(ip) && ip.length() > ipLen) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            } else {
                ip = ip.substring(0, 16);
            }
        }
        return ip;
    }

    /**
     * 获取AddrFromRemoteAddrIp
     *
     * @return String
     */
    public static String getIpAddrFromRemoteAddr(HttpServletRequest request) {
        String ip = HttpRequestUtil.getIpAddrFromRemoteAddr(null, request);
        return HttpRequestUtil.parseIp(ip);
    }

    /**
     * 获取roxyClientIP
     *
     * @param request request请求
     * @return String
     */
    public static String getIpAddrFromProxyClient(String ip, HttpServletRequest request) {
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        return ip;
    }

    /**
     * 获取WLProxyClientIP
     *
     * @param request request请求
     * @return String
     */
    public static String getIpAddrFromWLProxyClient(String ip, HttpServletRequest request) {
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        return ip;
    }


    /**
     * 获取真实ip，防止ip伪造
     *
     * @param request request请求
     * @return String
     */
    private static String getIpAddrFromRemoteAddr(String ip, HttpServletRequest request) {
        ip = request.getHeader("X-Real-IP");
        if (StringUtil.isNotBlank(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (StringUtil.isNotBlank(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
}
