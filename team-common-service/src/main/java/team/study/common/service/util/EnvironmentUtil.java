package team.study.common.service.util;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 环境变量工具类
 *
 * @author JiaHao
 * @date 2022/11/20 18:48
 **/
@Configuration
public class EnvironmentUtil implements EnvironmentAware {

    private static Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        EnvironmentUtil.environment = environment;
    }

    /**
     * 获取环境变量
     *
     * @return
     */
    public static Environment getEnvironment() {
        return EnvironmentUtil.environment;
    }

}
