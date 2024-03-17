/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.user.UserAO;
import net.somta.juggle.console.domain.user.repository.IUserRepository;
import net.somta.juggle.console.infrastructure.converter.IUserConverter;
import net.somta.juggle.console.infrastructure.mapper.UserMapper;
import net.somta.juggle.console.infrastructure.po.UserPO;
import org.springframework.stereotype.Repository;

/**
 * @author husong
 * @since 1.0.0
 */
@Repository
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
