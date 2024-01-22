package net.somta.juggle.console.domain.object.vo;

import java.util.List;

/**
 * @author husong
 */
public class ObjectVO {

    private Long id;

    private String objectCode;

    private String objectName;

    private String objectDesc;

    private List<PropertyVO> propertyList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
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
