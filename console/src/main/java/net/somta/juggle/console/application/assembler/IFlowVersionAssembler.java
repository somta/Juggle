package net.somta.juggle.console.application.assembler;

import net.somta.juggle.console.domain.version.view.FlowVersionView;
import net.somta.juggle.console.domain.version.vo.FlowVersionQueryVO;
import net.somta.juggle.console.domain.version.vo.FlowVersionVO;
import net.somta.juggle.console.interfaces.dto.FlowVersionDTO;
import net.somta.juggle.console.interfaces.param.FlowVersionPageParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 */
@Mapper
public interface IFlowVersionAssembler {
    IFlowVersionAssembler IMPL = Mappers.getMapper(IFlowVersionAssembler.class);

    FlowVersionQueryVO paramToVo(FlowVersionPageParam flowVersionPageParam);

    List<FlowVersionDTO> voListToDtoList(List<FlowVersionVO> flowVersionVOList);

    default List<FlowVersionDTO> viewListToDtoList(List<FlowVersionView> flowVersionViewList){
        if ( flowVersionViewList == null ) {
            return null;
        }
        List<FlowVersionDTO> list = new ArrayList<>( flowVersionViewList.size() );
        for (FlowVersionView flowVersionView : flowVersionViewList ) {
            FlowVersionDTO flowVersionDTO = new FlowVersionDTO();
            flowVersionDTO.setId(flowVersionView.getId());
            flowVersionDTO.setFlowName(flowVersionView.getFlowName());
            flowVersionDTO.setFlowVersion(flowVersionView.getFlowVersion());
            flowVersionDTO.setFlowVersionStatus(flowVersionView.getFlowVersionStatus());
            flowVersionDTO.setTriggerUrl(JUGGLE_SERVER_VERSION + "/flow/version/trigger/"+flowVersionView.getFlowVersion()+"/"+flowVersionView.getFlowKey());
            list.add(flowVersionDTO);
        }
        return list;
    }
}
