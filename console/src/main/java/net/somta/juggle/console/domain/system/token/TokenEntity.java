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
package net.somta.juggle.console.domain.system.token;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.domain.system.token.vo.OpenApiTokenVO;

import java.util.Base64;

/**
 * @author husong
 * @since 1.1.0
 */
public class TokenEntity {


    public OpenApiTokenVO parseTokenValue(String encoderStr){
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encoderStr);
        OpenApiTokenVO openApiTokenVo = JsonSerializeHelper.deserialize(new String(decodedBytes),OpenApiTokenVO.class);
        return openApiTokenVo;
    }

    public String generateTokenValue(){
        OpenApiTokenVO openApiTokenVo = new OpenApiTokenVO();
        openApiTokenVo.setUserId(IdentityContext.getIdentity().getUserId());
        String tokenString = JsonSerializeHelper.serialize(openApiTokenVo);
        String tokenValue = Base64.getUrlEncoder().encodeToString(tokenString.getBytes());
        return tokenValue;
    }

}
