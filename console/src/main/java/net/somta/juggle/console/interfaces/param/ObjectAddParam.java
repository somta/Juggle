package net.somta.juggle.console.interfaces.param;

import net.somta.juggle.console.domain.object.vo.PropertyVO;
import net.somta.juggle.core.model.Property;

import java.util.List;

/**
 * @author Gavin
 */
public class ObjectAddParam {

    private String objectKey;

    private String objectName;

    private String objectDesc;

    private List<Property> props;

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

    public List<Property> getProps() {
        return props;
    }

    public void setProps(List<Property> props) {
        this.props = props;
    }
}
