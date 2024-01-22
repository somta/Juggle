package net.somta.juggle.console.infrastructure.mapper;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.po.ObjectPO;

/**
 * @author Gavin
 */
public interface ObjectMapper extends IBaseMapper {


    int addObject(ObjectPO objectPo);
}
