package net.somta.juggle.console.infrastructure.mapper;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.po.ObjectPO;
import net.somta.juggle.console.infrastructure.view.ObjectInfoView;

import java.util.List;

/**
 * @author Gavin
 */
public interface ObjectMapper extends IBaseMapper {


    int addObject(ObjectPO objectPo);

    ObjectPO queryObjectInfoByKey(String objectKey);

    List<ObjectInfoView> queryObjectInfoList();
}
