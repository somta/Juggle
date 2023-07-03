package net.somta.juggle.console.infrastructure.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.model.User;

public interface UserMapper extends IBaseMapper {

    User getUserByUserName(String userName);

}
