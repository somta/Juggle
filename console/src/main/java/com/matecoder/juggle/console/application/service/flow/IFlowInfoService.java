package com.matecoder.juggle.console.application.service.flow;

import com.github.pagehelper.PageInfo;
import com.matecoder.juggle.console.interfaces.dto.flow.FlowInfoDTO;
import com.matecoder.juggle.console.interfaces.param.flow.FlowInfoPageParam;

/**
 * @author husong
 */
public interface IFlowInfoService {

    /**
     * Delete flow information based on flow ID
     * @param flowId flow id
     * @return Boolean
     */
    Boolean deleteFlowInfo(Long flowId);

    /**
     * Query flow information pagination list
     * @param flowInfoPageParam Flow information pagination query object
     * @return Flow Information Paging Object
     */
    PageInfo<FlowInfoDTO> getFlowInfoPageList(FlowInfoPageParam flowInfoPageParam);
}
