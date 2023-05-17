package net.somta.juggle.console.web.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.enums.UserErrorEnum;
import net.somta.juggle.console.model.User;
import net.somta.juggle.console.model.param.LoginParam;
import net.somta.juggle.console.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;


@Tag(name = "登录接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/user")
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * 获取API列表
     * @return Boolean
     */
    @Operation(summary = "登录")
    @PostMapping("/login")
    public ResponseDataResult<String> login(@RequestBody LoginParam loginParam){
        if(StringUtils.isEmpty(loginParam.getUserName()) || StringUtils.isEmpty(loginParam.getPassword())){
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.LOGIN_PARAM_ERROR);
        }
        User user = userService.queryUserByUserName(loginParam.getUserName());
        if(user == null){
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.USER_NOT_EXIST_ERROR);
        }
        if(loginParam.getPassword().equals(user.getPassword())){
            /*Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            String token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);*/
            return ResponseDataResult.setResponseResult("234234");
        }else {
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.USER_PWD_ERROR);
        }
    }

}
