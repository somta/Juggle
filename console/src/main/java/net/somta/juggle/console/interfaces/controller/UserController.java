package net.somta.juggle.console.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.domain.user.enums.UserErrorEnum;
import net.somta.juggle.console.infrastructure.model.User;
import net.somta.juggle.console.domain.user.vo.UserTokenVO;
import net.somta.juggle.console.interfaces.dto.UserDTO;
import net.somta.juggle.console.interfaces.param.user.LoginParam;
import net.somta.juggle.console.interfaces.param.user.UpdatePasswordParam;
import net.somta.juggle.console.application.service.IUserService;
import net.somta.juggle.console.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;


/**
 * @author Gavin
 */
@Tag(name = "登录接口（已完成）")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/user")
public class UserController {

    @Autowired
    private IUserService userService;

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
            Map<String, Object> payload = new HashMap<>();
            payload.put(UserTokenVO.USER_ID, user.getId().toString());
            String token = JwtUtil.generateToken(payload);
            return ResponseDataResult.setResponseResult(token);
        }else {
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.USER_PWD_ERROR);
        }
    }

    @Operation(summary = "获取用户信息")
    @PostMapping("/getUserInfo")
    public ResponseDataResult<UserDTO> getUserInfo(HttpServletRequest request){
        UserDTO userDTO = new UserDTO();
        String token = request.getHeader(JwtUtil.TOKEN_HEADER_KEY);
        UserTokenVO userTokenVO = JwtUtil.parseToken(token);
        User user = userService.queryById(userTokenVO.getUserId());
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        return ResponseDataResult.setResponseResult(userDTO);
    }

    @Operation(summary = "修改密码")
    @PutMapping("/updatePassword")
    public ResponseDataResult<Boolean> updatePassword(UpdatePasswordParam updatePasswordParam){
        User user = userService.queryById(updatePasswordParam.getUserId());
        if(user == null){
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.USER_NOT_EXIST_ERROR);
        }
        if(!updatePasswordParam.getOldPassword().equals(user.getPassword())){
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.OLD_PASSWORD_ERROR);
        }
        user.setPassword(updatePasswordParam.getNewPassword());
        userService.update(user);
        return ResponseDataResult.setResponseResult(true);
    }

}
