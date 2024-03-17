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
package net.somta.juggle.console.application.service.impl;

import net.somta.juggle.console.domain.user.UserAO;
import net.somta.juggle.console.domain.user.repository.IUserRepository;
import net.somta.juggle.console.application.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author husong
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Boolean updateUser(UserAO userAo) {
        userRepository.updateUser(userAo);
        return true;
    }

    @Override
    public UserAO queryUserById(Long userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public UserAO queryUserByUserName(String userName) {
        UserAO userAo = userRepository.getUserByUserName(userName);
        return userAo;
    }
}
