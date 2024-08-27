package net.somta.juggle.console.infrastructure.converter.suite;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.suite.api.ApiAO;
import net.somta.juggle.console.domain.suite.api.vo.ApiVO;
import net.somta.juggle.console.domain.suite.api.vo.HeaderVO;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.infrastructure.po.suite.ApiPO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IApiConverter {
    IApiConverter IMPL = Mappers.getMapper(IApiConverter.class);

    ApiAO poToAo(ApiPO apiPo);

    @Mapping(target = "apiRequestType", expression = "java(apiAo.getApiRequestType().name())")
    ApiPO aoToPo(ApiAO apiAo);

    List<ApiVO> poListToVoList(List<ApiPO> apiList);

    default List<ParameterPO> headerListToParameterList(Long sourceId, List<HeaderVO> headerList){
        List<ParameterPO> headerPoList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(headerList)){
            Date currentDate = new Date();
            ParameterPO parameterPo = null;
            for (HeaderVO headerVo: headerList) {
                parameterPo = new ParameterPO();
                parameterPo.setParamKey(headerVo.getHeaderKey());
                parameterPo.setParamName(headerVo.getHeaderName());
                parameterPo.setParamType(ParameterTypeEnum.HEADER.getCode());
                parameterPo.setDataType(JsonSerializeHelper.serialize(headerVo.getDataType()));
                parameterPo.setParamDesc(headerVo.getHeaderDesc());
                parameterPo.setSourceType(ParameterSourceTypeEnum.API.getCode());
                parameterPo.setRequired(headerVo.getRequired());
                parameterPo.setSourceId(sourceId);
                parameterPo.setCreatedAt(currentDate);
                headerPoList.add(parameterPo);
            }
        }
        return headerPoList;
    }
}
