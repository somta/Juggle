package net.somta.juggle.console.domain.obj;

import net.somta.juggle.console.domain.obj.vo.PropertyVO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author husong
 */
public class ObjAO {

    private Long id;

    private String objCode;

    private String objName;

    private String objDesc;

    private List<PropertyVO> propertyList;


    public void initPropertyList(List<PropertyVO> objPropertyList) {
        this.propertyList = objPropertyList;
    }

    public void parseProperty(List<ParameterPO> propertyList) {
        this.propertyList = new ArrayList<>(propertyList.size());
        if(CollectionUtils.isNotEmpty(propertyList)){
            PropertyVO propertyVo = null;
            for (ParameterPO parameterPo : propertyList) {
                propertyVo = new PropertyVO();
                propertyVo.setPropKey(parameterPo.getParamKey());
                propertyVo.setPropName(parameterPo.getParamName());
                propertyVo.setDataType(parameterPo.getDataType());
                this.propertyList.add(propertyVo);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjCode() {
        return objCode;
    }

    public void setObjCode(String objCode) {
        this.objCode = objCode;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getObjDesc() {
        return objDesc;
    }

    public void setObjDesc(String objDesc) {
        this.objDesc = objDesc;
    }

    public List<PropertyVO> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<PropertyVO> propertyList) {
        this.propertyList = propertyList;
    }


}
