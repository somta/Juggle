package net.somta.juggle.console.web.controller;

import net.somta.juggle.core.model.FlowDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一个测试的Controller，供流程执行器调用测试方法节点逻辑
 */
@RestController
public class TestController {

    /**
     * 测试方法
     * @param id 用户ID
     * @return Boolean
     */
    @GetMapping("/test/info")
    public Boolean triggerFlow(Integer id){
        System.out.println("接收到的用户ID为："+id);
        return true;
    }

}
