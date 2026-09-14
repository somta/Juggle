package com.matecoder.juggle.console.domain.user.repository;

import com.matecoder.juggle.console.domain.user.UserAO;

/**
 * @author Gavin
 */
public interface IUserRepository {

    void updateUser(UserAO userAo);

    UserAO getUserById(Long userId);

    UserAO getUserByUserName(String userName);

}
