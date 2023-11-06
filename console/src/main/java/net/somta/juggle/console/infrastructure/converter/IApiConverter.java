package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.api.ApiAO;
import net.somta.juggle.console.domain.api.vo.ApiVO;
import net.somta.juggle.console.infrastructure.po.ApiPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IApiConverter {
    IApiConverter IMPL = Mappers.getMapper(IApiConverter.class);

    ApiAO poToAo(ApiPO apiPo);

    List<ApiVO> poListToVoList(List<ApiPO> apiList);

}
