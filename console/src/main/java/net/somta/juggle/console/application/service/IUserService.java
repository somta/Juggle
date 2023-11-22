package net.somta.juggle.console.application.service;

import net.somta.juggle.console.domain.user.UserAO;

/**
 * @author husong
 */
public interface IUserService {

    /**
     * Update user Information
     * @param userAo User Information
     * @return boolean
     */
    Boolean updateUser(UserAO userAo);

    /**
     * Query user information based on userId
     * @param userId userid
     * @return User Information
     */
    UserAO queryUserById(Long userId);

    /**
     * Query user information based on userName
     * @param userName user
     * @return User Information
     */
    UserAO queryUserByUserName(String userName);

}
