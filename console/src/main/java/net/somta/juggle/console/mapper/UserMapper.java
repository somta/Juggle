package net.somta.juggle.console.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.model.User;

public interface UserMapper extends IBaseMapper {

    User getUserByUserName(String userName);

}
