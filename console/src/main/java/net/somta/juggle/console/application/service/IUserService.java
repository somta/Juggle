package net.somta.juggle.console.application.service;

import net.somta.core.base.IBaseService;
import net.somta.juggle.console.infrastructure.po.UserPO;

public interface IUserService extends IBaseService<UserPO> {
    UserPO queryUserByUserName(String userName);

}
