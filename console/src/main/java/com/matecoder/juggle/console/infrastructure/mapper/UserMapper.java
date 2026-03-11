package com.matecoder.juggle.console.infrastructure.mapper;


import com.matecoder.core.base.IBaseMapper;
import com.matecoder.juggle.console.infrastructure.po.UserPO;

/**
 * @author husong
 */
public interface UserMapper extends IBaseMapper {

    UserPO getUserByUserName(String userName);

}
