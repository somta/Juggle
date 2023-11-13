package net.somta.juggle.console.interfaces.controller.example;

import net.somta.juggle.console.interfaces.handler.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;

/**
 * 用户的示例接口，为系统内置流程提供接口示例
 * @author husong
 */
@RestController
@RequestMapping("/example/user")
public class UserExampleController {

    private final static Logger logger = LoggerFactory.getLogger(UserExampleController.class);

    /**
     * 模拟登录接口
     * @param loginParam
     * @return
     */
    @PostMapping("/login")
    public Boolean login(@RequestBody LoginParam loginParam){
        logger.info("接收到的用户名为:{},接收到的密码为:{}",loginParam.getUserName(),loginParam.getPassword());
        System.out.println("接收到的用户名为："+loginParam);
        if("juggle".equals(loginParam.userName) && "123456".equals(loginParam.getPassword())){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 模拟获取用户信息
     * @param id 用户ID
     * @return 用户姓名
     */
    @GetMapping("/getUserById")
    public User getUserById(Integer id){
        logger.info("接收到的用户ID为:{}",id);
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        user.setBirthday(new Date());
        return user;
    }

    class LoginParam {
        private String userName;
        private String password;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    class User {
        private String name;
        private Integer age;
        private Date birthday;

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

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
    }

}
