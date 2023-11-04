package net.somta.juggle.console.interfaces.controller.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户的示例接口，为系统内置流程提供接口示例
 */
@RestController
public class UserExampleController {

    /**
     * 测试方法
     * @param id 用户ID
     * @return 用户姓名
     */
    @GetMapping("/test/getUserById")
    public User getUserById(Integer id){
        System.out.println("接收到的用户ID为："+id);
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        return user;
    }

    /**
     * 测试方法
     * @param user 用户
     * @return 用户姓名
     */
    @GetMapping("/test/updateUser")
    public User updateUser(User user){
        System.out.println("接收到的用户ID为："+user.getName());
        return user;
    }

    class User {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

}
