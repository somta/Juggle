package net.somta.juggle.console.domain.object;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.object.vo.PropertyVO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Property;
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

    private List<Property> propertyList;


    public void initPropertyList(List<Property> objPropertyList) {
        this.propertyList = objPropertyList;
    }

    public void parseProperty(List<ParameterPO> propertyList) {
        this.propertyList = new ArrayList<>(propertyList.size());
        if(CollectionUtils.isNotEmpty(propertyList)){
            Property property = null;
            for (ParameterPO parameterPo : propertyList) {
                property = new Property();
                property.setPropKey(parameterPo.getParamKey());
                property.setPropName(parameterPo.getParamName());
                property.setDataType(JsonSerializeHelper.deserialize(parameterPo.getDataType(),DataType.class));
                this.propertyList.add(property);
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

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }


}
