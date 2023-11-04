package net.somta.juggle.console.application.service.impl;

import net.somta.juggle.console.domain.user.UserAO;
import net.somta.juggle.console.domain.user.repository.IUserRepository;
import net.somta.juggle.console.application.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author husong
 */
@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Boolean updateUser(UserAO userAO) {
        userRepository.updateUser(userAO);
        return true;
    }

    @Override
    public UserAO queryUserById(Long userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public UserAO queryUserByUserName(String userName) {
        UserAO userAO = userRepository.getUserByUserName(userName);
        return userAO;
    }
}
