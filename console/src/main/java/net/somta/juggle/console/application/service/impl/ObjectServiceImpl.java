package net.somta.juggle.console.application.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.application.assembler.IObjectAssembler;
import net.somta.juggle.console.application.service.IObjectService;
import net.somta.juggle.console.domain.datatype.repository.IDataTypeInfoRepository;
import net.somta.juggle.console.domain.object.ObjectAO;
import net.somta.juggle.console.domain.object.repository.IObjectRepository;
import net.somta.juggle.console.domain.object.vo.ObjectVO;
import net.somta.juggle.console.interfaces.dto.ObjectDTO;
import net.somta.juggle.console.interfaces.dto.ObjectInfoDTO;
import net.somta.juggle.console.interfaces.param.ObjectAddParam;
import net.somta.juggle.console.interfaces.param.ObjectQueryParam;
import net.somta.juggle.console.interfaces.param.ObjectUpdateParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author husong
 */
@Service
public class ObjectServiceImpl implements IObjectService {
    private final IObjectRepository objRepository;
    private final IDataTypeInfoRepository dataTypeInfoRepository;

    public ObjectServiceImpl(IObjectRepository objRepository, IDataTypeInfoRepository dataTypeInfoRepository) {
        this.objRepository = objRepository;
        this.dataTypeInfoRepository = dataTypeInfoRepository;
    }

    @Override
    public Boolean addObject(ObjectAddParam objectAddParam) {
        ObjectAO objectAo = IObjectAssembler.IMPL.paramToAo(objectAddParam);
        objectAo.initPropertyList(objectAddParam.getProps());
        return objRepository.addObject(objectAo);
    }

    @Override
    public Boolean deleteObject(Long objectId) {
        objRepository.deleteObjectById(objectId);
        ObjectAO objectAo = objRepository.queryObject(objectId);
        return true;
    }

    @Override
    public Boolean updateObject(ObjectUpdateParam objectUpdateParam) {
        ObjectAO objectAO = IObjectAssembler.IMPL.paramToAo(objectUpdateParam);
        objectAO.initPropertyList(objectUpdateParam.getProps());
        return objRepository.updateObject(objectAO);
    }

    @Override
    public ObjectInfoDTO getObjectInfo(Long objectId) {
        ObjectAO objectAo = objRepository.queryObject(objectId);
        ObjectInfoDTO objectInfoDto = IObjectAssembler.IMPL.aoToDto(objectAo);
        return objectInfoDto;
    }

    @Override
    public List<ObjectDTO> getObjectList() {
        List<ObjectVO> objectVoList = objRepository.queryObjectList();
        List<ObjectDTO> objList = IObjectAssembler.IMPL.voListToDtoList(objectVoList);
        return objList;
    }

    @Override
    public PageInfo getObjectPageList(ObjectQueryParam objectQueryParam) {
        Page<ObjectDTO> page = PageHelper.startPage(objectQueryParam.getPageNum(), objectQueryParam.getPageSize());
        List<ObjectVO> objectVoList = objRepository.queryObjectPageList(objectQueryParam);
        List<ObjectDTO> objList = IObjectAssembler.IMPL.voListToDtoList(objectVoList);
        PageInfo pageInfo = new PageInfo(objList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }
}
