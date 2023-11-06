package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.user.UserAO;
import net.somta.juggle.console.infrastructure.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author husong
 */
@Mapper
public interface IUserConverter {
    IUserConverter IMPL = Mappers.getMapper(IUserConverter.class);

    UserAO poToAo(UserPO userPo);

    UserPO aoToPo(UserAO userAo);
}
