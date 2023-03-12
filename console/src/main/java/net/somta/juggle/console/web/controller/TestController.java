package net.somta.juggle.console.web.controller;

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
     * @return 用户姓名
     */
    @GetMapping("/test/info")
    public User triggerFlow(Integer id){
        System.out.println("接收到的用户ID为："+id);
        User user = new User();
        user.setName("张三");
        return user;
    }

    class User {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
