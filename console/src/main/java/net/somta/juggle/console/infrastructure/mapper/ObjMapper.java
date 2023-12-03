package net.somta.juggle.console.infrastructure.mapper;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.po.ObjPO;

/**
 * @author Gavin
 */
public interface ObjMapper extends IBaseMapper {


    int addObj(ObjPO objPo);
}
