package team.study.common.rpc.config;

/**
 * client配置
 *
 * @author JiaHao
 * @date 2022/11/20 18:37
 **/
public class DemoConstants {

    public static final String DEFAULT_SERVICE_NAME = "${common.client.name:common}";

    public static class Demo {
        public static final String NAME = "demo";
        public static final String SERVICE_NAME = "${" + NAME + ".client.name:" + DEFAULT_SERVICE_NAME + "}";
    }

}
