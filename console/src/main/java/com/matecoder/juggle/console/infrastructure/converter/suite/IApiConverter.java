package com.matecoder.juggle.console.infrastructure.converter.suite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.matecoder.common.utils.JsonUtil;
import com.matecoder.juggle.console.domain.suite.api.ApiAO;
import com.matecoder.juggle.console.domain.suite.api.vo.ApiVO;
import com.matecoder.juggle.console.domain.suite.api.vo.HeaderVO;
import com.matecoder.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import com.matecoder.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import com.matecoder.juggle.console.infrastructure.po.suite.ApiPO;
import com.matecoder.juggle.console.infrastructure.po.ParameterPO;
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
                parameterPo.setParamKey(headerVo.getParamKey());
                parameterPo.setParamName(headerVo.getParamName());
                parameterPo.setParamType(ParameterTypeEnum.HEADER.getCode());
                try {
                    parameterPo.setDataType(JsonUtil.serialize(headerVo.getDataType()));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                parameterPo.setParamDesc(headerVo.getParamDesc());
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
