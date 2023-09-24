package net.somta.juggle.console.application.service.impl;

import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.mapper.UserMapper;
import net.somta.juggle.console.infrastructure.po.UserPO;
import net.somta.juggle.console.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserPO>  implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IBaseMapper getMapper() {
        return userMapper;
    }

    @Override
    public UserPO queryUserByUserName(String userName) {
        UserPO userPO = userMapper.getUserByUserName(userName);
        return userPO;
    }
}
