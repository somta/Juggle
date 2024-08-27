package net.somta.juggle.console.infrastructure.converter.suite;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.domain.suite.api.vo.HeaderVO;
import net.somta.juggle.console.domain.suite.suiteinfo.SuiteEntity;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketApiVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketInfoVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import net.somta.juggle.console.infrastructure.po.suite.SuitePO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface ISuiteConverter {
    ISuiteConverter IMPL = Mappers.getMapper(ISuiteConverter.class);

    SuitePO entityToPo(SuiteEntity suiteEntity);

    List<SuiteVO> poListToVoList(List<SuitePO> suitePoList);

    SuiteVO poToVo(SuitePO suitePo);

    default SuiteMarketVO voToVo(SuiteMarketInfoVO suiteMarketInfoVo){
        SuiteMarketVO suiteMarketVo = new SuiteMarketVO();
        if(suiteMarketInfoVo == null){
            return suiteMarketVo;
        }
        suiteMarketVo.setId(suiteMarketInfoVo.getId());
        suiteMarketVo.setSuiteCode(suiteMarketInfoVo.getSuiteCode());
        suiteMarketVo.setSuiteName(suiteMarketInfoVo.getSuiteName());
        suiteMarketVo.setSuiteType(suiteMarketInfoVo.getSuiteType());
        suiteMarketVo.setSuiteImage(suiteMarketInfoVo.getSuiteImage());
        suiteMarketVo.setSuiteVersion(suiteMarketInfoVo.getSuiteVersion());
        suiteMarketVo.setPriceStatus(suiteMarketInfoVo.getPriceStatus());
        suiteMarketVo.setSuitePrice(suiteMarketInfoVo.getSuitePrice());
        suiteMarketVo.setSuiteHelpDocJson(suiteMarketInfoVo.getSuiteHelpDocJson());
        if(CollectionUtils.isNotEmpty(suiteMarketInfoVo.getApiList())){
            List<SuiteMarketApiVO> apiList = new ArrayList<>();
            List<SuiteMarketInfoVO.ApiInfoVO> apiInfoList = suiteMarketInfoVo.getApiList();
            SuiteMarketApiVO suiteMarketApiVo = null;
            for (SuiteMarketInfoVO.ApiInfoVO apiInfo : apiInfoList){
                suiteMarketApiVo = new SuiteMarketApiVO();
                suiteMarketApiVo.setId(apiInfo.getId());
                suiteMarketApiVo.setApiUrl(apiInfo.getApiUrl());
                suiteMarketApiVo.setApiName(apiInfo.getApiName());
                suiteMarketApiVo.setApiDesc(apiInfo.getApiDesc());
                suiteMarketApiVo.setApiRequestType(apiInfo.getApiRequestType());
                suiteMarketApiVo.setApiRequestContentType(apiInfo.getApiRequestContentType());
                if(StringUtils.isNotEmpty(apiInfo.getApiHeaders())){
                    suiteMarketApiVo.setApiHeaders(JsonSerializeHelper.deserialize(apiInfo.getApiHeaders(),List.class, HeaderVO.class));
                }
                if(StringUtils.isNotEmpty(apiInfo.getApiInputParameter())){
                    suiteMarketApiVo.setApiInputParameter(JsonSerializeHelper.deserialize(apiInfo.getApiInputParameter(),List.class, InputParameterVO.class));
                }
                if(StringUtils.isNotEmpty(apiInfo.getApiOutputParameter())){
                    suiteMarketApiVo.setApiOutputParameter(JsonSerializeHelper.deserialize(apiInfo.getApiOutputParameter(),List.class, OutputParameterVO.class));
                }
                apiList.add(suiteMarketApiVo);
            }
            suiteMarketVo.setApiList(apiList);
        }
        return suiteMarketVo;
    }
}
