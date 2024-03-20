/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.console.interfaces.controller.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 用户的示例接口，为系统内置流程提供接口示例
 * @author husong
 */
@Tag(name = "示例-用户的示例接口")
@RestController
@RequestMapping("/example/user")
public class UserExampleController {

    private final static Logger logger = LoggerFactory.getLogger(UserExampleController.class);

    /**
     * 模拟登录接口
     * @param loginParam
     * @return
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public LoginDTO login(@RequestBody LoginParam loginParam){
        logger.info("接收到的用户名为:{},接收到的密码为:{}",loginParam.getUserName(),loginParam.getPassword());
        LoginDTO loginDto = new LoginDTO();
        if("juggle".equals(loginParam.userName) && "123456".equals(loginParam.getPassword())){
            loginDto.setUserId(1);
            loginDto.setUserName(loginParam.getUserName());
            loginDto.setLoginFlag(true);
        }else {
            loginDto.setLoginFlag(false);
        }
        return loginDto;
    }

    /**
     * 模拟获取用户信息
     * @param userId 用户ID
     * @return 用户姓名
     */
    @Operation(summary = "获取用户信息")
    @GetMapping("/getUserById")
    public User getUserById(Integer userId){
        logger.info("接收到的用户ID为:{}",userId);
        User user = new User();
        user.setId(userId);
        user.setName("张三");
        user.setAge(18);
        user.setBirthday(new Date());
        return user;
    }

    public static class LoginParam {
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

    public static class User {
        private Integer id;
        private String name;
        private Integer age;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date birthday;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

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

    public static class LoginDTO {
        private Integer userId;
        private String userName;
        private Boolean loginFlag;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Boolean getLoginFlag() {
            return loginFlag;
        }

        public void setLoginFlag(Boolean loginFlag) {
            this.loginFlag = loginFlag;
        }
    }
}
