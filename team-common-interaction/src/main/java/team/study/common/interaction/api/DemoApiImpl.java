package team.study.common.interaction.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import team.study.common.api.api.DemoApi;

/**
 * rpc调用实现类demo
 *
 * @author JiaHao
 * @date 2022/11/20 18:33
 **/
@Api(tags = "rpc调用demo")
@RestController
public class DemoApiImpl implements DemoApi {

    @Override
    public void delete(Long id) {
        //进行删除逻辑服务调用，等于controller层调用service层
    }

}
