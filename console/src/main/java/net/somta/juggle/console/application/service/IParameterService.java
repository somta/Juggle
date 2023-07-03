package net.somta.juggle.console.application.service;

import net.somta.core.base.IBaseService;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.infrastructure.model.Parameter;

import java.util.List;

public interface IParameterService  extends IBaseService<Parameter> {

    List<Parameter> getParameterListByVO(ParameterVO parameterQueryVO);

}
