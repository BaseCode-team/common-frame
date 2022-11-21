package team.study.common.api.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.study.common.api.config.VersionConfig;

/**
 * rpc定义示例
 *
 * @author JiaHao
 * @date 2022/11/20 18:01
 **/
@RequestMapping(VersionConfig.COMMON_RPC_VERSION_URL + "demo")
public interface DemoApi {

    /**
     * 删除RPC接口实例
     *
     * @param id
     */
    @PostMapping
    void delete(@RequestParam("id") Long id);

}
