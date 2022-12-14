package team.study.common.base.config;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * 忽略Token
 *
 * @author JiaHao
 * @date 2022-12-13 22:38
 */
@Data
@ConfigurationProperties(prefix = IgnoreProperties.PREFIX)
public class IgnoreProperties {
    public static final String PREFIX = "my.ignore";
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    /**
     * 是否启用权限校验
     */
    private Boolean authEnabled = false;

    private List<String> baseUri = CollUtil.newArrayList(
            "/login",
            "/**/*.css",
            "/**/*.js",
            "/**/*.html",
            "/**/*.ico",
            "/**/*.jpg",
            "/**/*.jpeg",
            "/**/*.png",
            "/**/*.gif",
            "/**/v2/**",
            "/**/api-docs/**",
            "/**/api-docs-ext/**",
            "/**/swagger-resources/**",
            "/**/webjars/**",
            "/actuator/**",
            "/**/static/**",
            "/**/public/**",
            "/error",
            // api 扫描
            "/*/systemApiScan",
            // 表单验证
            "/*/form/validator/**",
            // 不需要租户编码、不需要登录、不需要权限即可访问的接口
            "/**/anno/**"
    );

    public void addIgnore(String path) {
        this.baseUri.add(path);
    }

    public void addIgnore(List<String> path) {
        this.baseUri.addAll(path);
    }

    /**
     * 不需要携带 租户ID, 也不校验是否登录（token）。  即： 请求头中不携带 tenant
     * <p>
     * 注意： 此类接口，无法操作租户库(base_0000、extend_0000)的数据，因为后台无法获取租户信息，导致无法切换数据库
     *
     * @see 3.x
     */
    private List<String> tenant = CollUtil.newArrayList("/**/noTenant/**", "/**/anyTenant/**");

    /**
     * 不需要登录, 且不需要校验权限
     * <p>
     * 注意： 此类接口，可以正常操作租户库(base_0000、extend_0000)的数据
     * 注意： 此类接口，无法获取当前请求的用户信息(userId)
     *
     * <p>
     * 如： 门户网站的接口，登录页面获取系统配置的接口等
     */
    private List<String> token = CollUtil.newArrayList("/**/noToken/**", "/ds/**");

    public boolean isIgnoreToken(String path) {
        List<String> all = new ArrayList<>();
        all.addAll(getBaseUri());
        all.addAll(getToken());
        return all.stream().anyMatch(url -> path.startsWith(url) || ANT_PATH_MATCHER.match(url, path));
    }

    public boolean isIgnoreTenant(String path) {
        List<String> all = new ArrayList<>();
        all.addAll(getBaseUri());
        all.addAll(getTenant());
        return all.stream().anyMatch(url -> path.startsWith(url) || ANT_PATH_MATCHER.match(url, path));
    }


}

