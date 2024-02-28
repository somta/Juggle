package net.somta.juggle.console.interfaces.dto;

import net.somta.juggle.console.domain.object.vo.PropertyVO;

import java.util.List;

/**
 * @author Gavin
 */
public class ObjectInfoDTO {

    private Long id;

    private String objectKey;

    private String objectName;

    private String objectDesc;

    private List<PropertyVO> props;

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

    public List<PropertyVO> getProps() {
        return props;
    }

    public void setProps(List<PropertyVO> props) {
        this.props = props;
    }
}
