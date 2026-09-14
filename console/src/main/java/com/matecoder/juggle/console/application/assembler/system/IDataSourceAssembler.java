package com.matecoder.juggle.console.application.assembler.system;

import com.matecoder.juggle.console.domain.system.datasource.DataSourceAO;
import com.matecoder.juggle.console.domain.system.datasource.vo.DataSourceQueryVO;
import com.matecoder.juggle.console.domain.system.datasource.vo.DataSourceVO;
import com.matecoder.juggle.console.interfaces.dto.system.DataSourceDTO;
import com.matecoder.juggle.console.interfaces.param.system.DataSourceAddParam;
import com.matecoder.juggle.console.interfaces.param.system.DataSourceQueryParam;
import com.matecoder.juggle.console.interfaces.param.system.DataSourceUpdateParam;
import com.matecoder.juggle.core.model.DataSource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 * @since 1.2.0
 */
@Mapper
public interface IDataSourceAssembler {

    IDataSourceAssembler IMPL = Mappers.getMapper(IDataSourceAssembler.class);

    DataSourceAO paramToAo(DataSourceAddParam dataSourceAddParam);

    DataSourceAO paramToAo(DataSourceUpdateParam dataSourceUpdateParam);

    DataSourceDTO aoToDto(DataSourceAO dataSourceAo);

    List<DataSourceDTO> voListToDtoList(List<DataSourceVO> dataSourceVoList);

    DataSourceQueryVO paramToVo(DataSourceQueryParam dataSourceQueryParam);

    DataSource aoToModel(DataSourceAO dataSourceAo);
}
