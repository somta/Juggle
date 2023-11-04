package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.user.UserAO;
import net.somta.juggle.console.domain.user.repository.IUserRepository;
import net.somta.juggle.console.infrastructure.converter.IUserConverter;
import net.somta.juggle.console.infrastructure.mapper.UserMapper;
import net.somta.juggle.console.infrastructure.po.UserPO;
import org.springframework.stereotype.Component;

/**
 * @author husong
 */
@Component
public class UserRepositoryImpl implements IUserRepository {
    private final UserMapper userMapper;

    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void updateUser(UserAO userAO) {
        UserPO userPO = IUserConverter.IMPL.aoToPo(userAO);
        userMapper.update(userPO);
    }

    @Override
    public UserAO getUserById(Long userId) {
        UserPO userPO = userMapper.queryById(userId);
        UserAO userAO = IUserConverter.IMPL.poToAo(userPO);
        return userAO;
    }

    @Override
    public UserAO getUserByUserName(String userName) {
        UserPO userPO = userMapper.getUserByUserName(userName);
        UserAO userAO = IUserConverter.IMPL.poToAo(userPO);
        return userAO;
    }
}
