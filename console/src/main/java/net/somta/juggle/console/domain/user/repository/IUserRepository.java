package net.somta.juggle.console.domain.user.repository;

import net.somta.juggle.console.domain.user.UserAO;

/**
 * @author Gavin
 */
public interface IUserRepository {

    void updateUser(UserAO userAO);

    UserAO getUserById(Long userId);

    UserAO getUserByUserName(String userName);

}
