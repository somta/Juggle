package net.somta.juggle.console.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.domain.user.UserAO;
import net.somta.juggle.console.domain.user.enums.UserErrorEnum;
import net.somta.juggle.console.infrastructure.po.UserPO;
import net.somta.juggle.console.domain.user.vo.UserTokenVO;
import net.somta.juggle.console.interfaces.dto.LoginDTO;
import net.somta.juggle.console.interfaces.dto.UserDTO;
import net.somta.juggle.console.interfaces.param.user.LoginParam;
import net.somta.juggle.console.interfaces.param.user.UpdatePasswordParam;
import net.somta.juggle.console.application.service.IUserService;
import net.somta.juggle.console.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;


/**
 * @author Gavin
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "登录")
    @PostMapping("/login")
    public ResponseDataResult<LoginDTO> login(@RequestBody LoginParam loginParam){
        LoginDTO loginDTO = new LoginDTO();
        if(StringUtils.isEmpty(loginParam.getUserName()) || StringUtils.isEmpty(loginParam.getPassword())){
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.LOGIN_PARAM_ERROR);
        }
        UserAO userAO = userService.queryUserByUserName(loginParam.getUserName());
        if(userAO == null){
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.USER_NOT_EXIST_ERROR);
        }
        if(loginParam.getPassword().equals(userAO.getPassword())){
            Map<String, Object> payload = new HashMap<>();
            payload.put(UserTokenVO.USER_ID, userAO.getId().toString());
            String token = JwtUtil.generateToken(payload);
            loginDTO.setUserName(userAO.getUserName());
            loginDTO.setToken(token);
            return ResponseDataResult.setResponseResult(loginDTO);
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
        UserAO userAO = userService.queryUserById(userTokenVO.getUserId());
        userDTO.setId(userAO.getId());
        userDTO.setUserName(userAO.getUserName());
        return ResponseDataResult.setResponseResult(userDTO);
    }

    @Operation(summary = "修改密码")
    @PutMapping("/updatePassword")
    public ResponseDataResult<Boolean> updatePassword(UpdatePasswordParam updatePasswordParam){
        UserAO userAO = userService.queryUserById(updatePasswordParam.getUserId());
        if(userAO == null){
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.USER_NOT_EXIST_ERROR);
        }
        if(!updatePasswordParam.getOldPassword().equals(userAO.getPassword())){
            return ResponseDataResult.setErrorResponseResult(UserErrorEnum.OLD_PASSWORD_ERROR);
        }
        userAO.setPassword(updatePasswordParam.getNewPassword());
        userService.updateUser(userAO);
        return ResponseDataResult.setResponseResult(true);
    }

}
