package net.somta.juggle.console.service;

import net.somta.core.base.IBaseService;
import net.somta.juggle.console.model.User;

public interface IUserService extends IBaseService<User> {
    User queryUserByUserName(String userName);

}
