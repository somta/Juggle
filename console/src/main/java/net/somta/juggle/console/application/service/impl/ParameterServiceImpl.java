package net.somta.juggle.console.application.service.impl;

import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.application.service.IParameterService;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.infrastructure.mapper.ParameterMapper;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterServiceImpl extends BaseServiceImpl<ParameterPO> implements IParameterService {

    @Autowired
    private ParameterMapper parameterMapper;
    @Override
    public IBaseMapper getMapper() {
        return parameterMapper;
    }

    @Override
    public List<ParameterPO> getParameterListByVO(ParameterVO parameterQueryVO) {
        return parameterMapper.getParameterListByVO(parameterQueryVO);
    }
}
