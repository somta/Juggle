package net.somta.juggle.console.infrastructure.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.po.UserPO;

/**
 * @author husong
 */
public interface UserMapper extends IBaseMapper {

    UserPO getUserByUserName(String userName);

}
