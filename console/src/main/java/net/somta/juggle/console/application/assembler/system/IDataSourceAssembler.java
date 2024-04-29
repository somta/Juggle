package net.somta.juggle.console.application.assembler.system;

import net.somta.juggle.console.domain.system.datasource.DataSourceAO;
import net.somta.juggle.console.domain.system.datasource.vo.DataSourceQueryVO;
import net.somta.juggle.console.domain.system.datasource.vo.DataSourceVO;
import net.somta.juggle.console.interfaces.dto.system.DataSourceDTO;
import net.somta.juggle.console.interfaces.param.system.DataSourceAddParam;
import net.somta.juggle.console.interfaces.param.system.DataSourceQueryParam;
import net.somta.juggle.console.interfaces.param.system.DataSourceUpdateParam;
import net.somta.juggle.core.model.DataSource;
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
