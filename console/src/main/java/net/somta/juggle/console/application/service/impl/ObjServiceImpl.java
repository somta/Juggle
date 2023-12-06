package net.somta.juggle.console.application.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.application.assembler.IApiAssembler;
import net.somta.juggle.console.application.assembler.IObjAssembler;
import net.somta.juggle.console.application.service.IObjService;
import net.somta.juggle.console.domain.api.ApiAO;
import net.somta.juggle.console.domain.api.vo.ApiVO;
import net.somta.juggle.console.domain.obj.ObjAO;
import net.somta.juggle.console.domain.obj.repository.IObjRepository;
import net.somta.juggle.console.domain.obj.vo.ObjVO;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.interfaces.dto.ApiInfoDTO;
import net.somta.juggle.console.interfaces.dto.ObjDTO;
import net.somta.juggle.console.interfaces.dto.ObjInfoDTO;
import net.somta.juggle.console.interfaces.param.ObjAddParam;
import net.somta.juggle.console.interfaces.param.ObjQueryParam;
import net.somta.juggle.console.interfaces.param.ObjUpdateParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author husong
 */
@Service
public class ObjServiceImpl implements IObjService {
    private final IObjRepository objRepository;

    public ObjServiceImpl(IObjRepository objRepository) {
        this.objRepository = objRepository;
    }

    @Override
    public Boolean addObj(ObjAddParam objAddParam) {
        ObjAO objAO = IObjAssembler.IMPL.paramToAo(objAddParam);
        objAO.initPropertyList(objAddParam.getProps());
        return objRepository.addObj(objAO);
    }

    @Override
    public Boolean deleteObj(Long objId) {
        return objRepository.deleteObjById(objId);
    }

    @Override
    public Boolean updateObj(ObjUpdateParam objUpdateParam) {
        ObjAO objAO = IObjAssembler.IMPL.paramToAo(objUpdateParam);
        objAO.initPropertyList(objUpdateParam.getProps());
        return objRepository.updateObj(objAO);
    }

    @Override
    public ObjInfoDTO getObjInfo(Long objId) {
        ObjAO objAo = objRepository.queryApi(objId);
        ObjInfoDTO objInfoDto = IObjAssembler.IMPL.aoToDto(objAo);
        return objInfoDto;
    }

    @Override
    public List<ObjInfoDTO> getObjList() {
        //todo 这里是一次全部查询出来  还是让前端根据对象在查询一次
        return null;
    }

    @Override
    public PageInfo getObjPageList(ObjQueryParam objQueryParam) {
        Page<ObjDTO> page = PageHelper.startPage(objQueryParam.getPageNum(), objQueryParam.getPageSize());
        List<ObjVO> objVoList = objRepository.queryObjPageList(objQueryParam);
        List<ObjDTO> objList = IObjAssembler.IMPL.voListToDtoList(objVoList);
        PageInfo pageInfo = new PageInfo(objList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }
}
