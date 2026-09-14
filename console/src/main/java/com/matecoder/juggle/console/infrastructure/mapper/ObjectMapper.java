package com.matecoder.juggle.console.infrastructure.mapper;

import com.matecoder.core.base.IBaseMapper;
import com.matecoder.juggle.console.infrastructure.po.ObjectPO;
import com.matecoder.juggle.console.infrastructure.view.ObjectInfoView;

import java.util.List;

/**
 * @author Gavin
 */
public interface ObjectMapper extends IBaseMapper {


    int addObject(ObjectPO objectPo);

    ObjectPO queryObjectInfoByKey(String objectKey);

    List<ObjectInfoView> queryObjectInfoList();
}
