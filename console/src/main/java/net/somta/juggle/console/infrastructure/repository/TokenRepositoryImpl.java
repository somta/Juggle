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

import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.domain.token.repository.ITokenRepository;
import net.somta.juggle.console.domain.token.vo.TokenVO;
import net.somta.juggle.console.infrastructure.converter.ITokenConverter;
import net.somta.juggle.console.infrastructure.mapper.TokenMapper;
import net.somta.juggle.console.infrastructure.po.TokenPO;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author husong
 * @since 1.1.0
 */
@Repository
public class TokenRepositoryImpl implements ITokenRepository {

    private final TokenMapper tokenMapper;

    public TokenRepositoryImpl(TokenMapper tokenMapper) {
        this.tokenMapper = tokenMapper;
    }

    @Override
    public void addToken(String tokenValue, String tokenDesc) {
        TokenPO tokenPo = new TokenPO();
        tokenPo.setTokenValue(tokenValue);
        tokenPo.setTokenDesc(tokenDesc);
        tokenPo.setCreatedAt(new Date());
        tokenPo.setCreatedBy(IdentityContext.getIdentity().getUserId());
        tokenMapper.add(tokenPo);
    }

    @Override
    public void deleteTokenById(Long tokenId) {
        tokenMapper.deleteById(tokenId);
    }

    @Override
    public void updateToken(Long tokenId, String tokenDesc) {
        TokenPO tokenPo = new TokenPO();
        tokenPo.setId(tokenId);
        tokenPo.setTokenDesc(tokenDesc);
        tokenPo.setUpdatedAt(new Date());
        tokenPo.setUpdatedBy(IdentityContext.getIdentity().getUserId());
        tokenMapper.update(tokenPo);
    }

    @Override
    public List<TokenVO> queryTokenList() {
        List<TokenPO> tokenPoList = tokenMapper.queryByList(null);
        return ITokenConverter.IMPL.poListToVoList(tokenPoList);
    }
}
