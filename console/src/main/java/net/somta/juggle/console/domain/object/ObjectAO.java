package net.somta.juggle.console.domain.object;

import net.somta.juggle.console.domain.object.vo.PropertyVO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author husong
 */
public class ObjectAO {

    private Long id;

    private String objectKey;

    private String objectName;

    private String objectDesc;

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

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectDesc() {
        return objectDesc;
    }

    public void setObjectDesc(String objectDesc) {
        this.objectDesc = objectDesc;
    }

    public List<PropertyVO> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<PropertyVO> propertyList) {
        this.propertyList = propertyList;
    }


}
