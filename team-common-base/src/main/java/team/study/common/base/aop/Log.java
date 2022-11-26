package team.study.common.base.aop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志类
 *
 * @author JiaHao
 * @date 2022 -11-25 10:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    /**
     * 描述
     *
     * @date 2022/11/25 10:57
     **/
    private String description;
    /**
     * 线程id
     *
     * @date 2022/11/25 10:33
     **/
    private String threadId;
    /**
     * 线程名称
     *
     * @date 2022/11/25 10:33
     **/
    private String threadName;
    /**
     * ip
     *
     * @date 2022/11/25 10:33
     **/
    private String ip;
    /**
     * url
     *
     * @date 2022/11/25 10:33
     **/
    private String url;
    /**
     * http方法 GET POST PUT DELETE PATCH
     *
     * @date 2022/11/25 10:33
     **/
    private String httpMethod;
    /**
     * 类方法
     *
     * @date 2022/11/25 10:33
     **/
    private String classMethod;
    /**
     * 请求参数
     *
     * @date 2022/11/25 10:33
     **/
    private Object requestParams;
    /**
     * 返回参数
     *
     * @date 2022/11/25 10:33
     **/
    private Object result;
    /**
     * 接口耗时
     *
     * @date 2022/11/25 10:33
     **/
    private Long timeCost;
    /**
     * 操作系统
     *
     * @date 2022/11/25 10:33
     **/
    private String os;
    /**
     * 浏览器
     *
     * @date 2022/11/25 10:33
     **/
    private String browser;
    /**
     * user-agent
     *
     * @date 2022/11/25 10:33
     **/
    private String userAgent;
}
