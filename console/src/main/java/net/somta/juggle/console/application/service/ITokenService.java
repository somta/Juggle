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
package net.somta.juggle.console.application.service;

import com.github.pagehelper.PageInfo;
import net.somta.core.base.page.PageParam;
import net.somta.juggle.console.interfaces.param.TokenUpdateParam;

/**
 * @author husong
 * @since 1.1.0
 */
public interface ITokenService {
    String addToken(String tokenDesc);

    void deleteToken(Long tokenId);

    void updateToken(TokenUpdateParam tokenUpdateParam);

    PageInfo getTokenPageList(PageParam pageParam);
}
