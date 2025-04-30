package net.somta.juggle.console.application.assembler.flow;

import net.somta.juggle.console.domain.flow.version.FlowVersionAO;
import net.somta.juggle.console.domain.flow.version.view.FlowVersionInfoView;
import net.somta.juggle.console.domain.flow.version.view.FlowVersionView;
import net.somta.juggle.console.domain.flow.version.vo.FlowVersionQueryVO;
import net.somta.juggle.console.domain.flow.version.vo.FlowVersionVO;
import net.somta.juggle.console.interfaces.dto.flow.FlowVersionDTO;
import net.somta.juggle.console.interfaces.param.flow.FlowVersionPageParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_OPEN_API_PREFIX;
import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 */
@Mapper
public interface IFlowVersionAssembler {
    IFlowVersionAssembler IMPL = Mappers.getMapper(IFlowVersionAssembler.class);

    /**
     * param assembler to vo
     * @param flowVersionPageParam flow version page param
     * @return FlowVersionQueryVO
     */
    FlowVersionQueryVO paramToVo(FlowVersionPageParam flowVersionPageParam);

    /**
     * voList assembler to FlowVersionDTO List
     * @param flowVersionVOList flowVersionVo List
     * @return FlowVersionDTO List
     */
    List<FlowVersionDTO> voListToDtoList(List<FlowVersionVO> flowVersionVOList);

    /**
     * view assembler to ao
     * @param flowVersionInfoView flow version view
     * @return flow version ao
     */
    @Mapping(target = "flowVersionStatusEnum", expression = "java(net.somta.juggle.console.domain.flow.version.enums.FlowVersionStatusEnum.getByCode(flowVersionInfoView.getFlowVersionStatus()))")
    FlowVersionAO viewToAo(FlowVersionInfoView flowVersionInfoView);

    /**
     * viewList assembler to DtoList
     * @param flowVersionViewList flowVersion List
     * @return FlowVersionDTO List
     */
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
            flowVersionDTO.setFlowVersionRemark(flowVersionView.getFlowVersionRemark());
            flowVersionDTO.setTriggerUrl(JUGGLE_OPEN_API_PREFIX + "/flow/trigger/"+flowVersionView.getFlowVersion()+"/"+flowVersionView.getFlowKey());
            list.add(flowVersionDTO);
        }
        return list;
    }
}
