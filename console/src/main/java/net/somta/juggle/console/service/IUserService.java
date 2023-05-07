package net.somta.juggle.console.service;

import net.somta.juggle.console.model.User;

public interface IUserService {
    User queryUserByUserName(String userName);

}
