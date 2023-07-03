package net.somta.juggle.console.application.service;

import net.somta.core.base.IBaseService;
import net.somta.juggle.console.infrastructure.model.User;

public interface IUserService extends IBaseService<User> {
    User queryUserByUserName(String userName);

}
