package net.somta.juggle.console.domain.obj.vo;

import java.util.List;

/**
 * @author husong
 */
public class ObjVO {

    private Long id;

    private String objCode;

    private String objName;

    private String objDesc;

    private List<PropertyVO> propertyList;

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
