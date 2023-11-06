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
    public void updateUser(UserAO userAo) {
        UserPO userPo = IUserConverter.IMPL.aoToPo(userAo);
        userMapper.update(userPo);
    }

    @Override
    public UserAO getUserById(Long userId) {
        UserPO userPo = userMapper.queryById(userId);
        UserAO userAo = IUserConverter.IMPL.poToAo(userPo);
        return userAo;
    }

    @Override
    public UserAO getUserByUserName(String userName) {
        UserPO userPo = userMapper.getUserByUserName(userName);
        UserAO userAo = IUserConverter.IMPL.poToAo(userPo);
        return userAo;
    }
}
