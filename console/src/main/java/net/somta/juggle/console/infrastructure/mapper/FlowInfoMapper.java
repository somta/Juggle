package net.somta.juggle.console.infrastructure.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.domain.flow.vo.FlowInfoQueryVO;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import net.somta.juggle.console.interfaces.param.FlowInfoPageParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author husong
 */
public interface FlowInfoMapper extends IBaseMapper {


    int addFlowInfo(FlowInfoPO flowInfoPO);

    FlowInfoPO querySimpleFlowInfo(Long flowId);

    /**
     * 根据流程key查询流程信息
     * @param flowKey
     * @return
     */
    FlowInfoPO queryFlowByFlowKey(@Param("flowKey") String flowKey);


}
