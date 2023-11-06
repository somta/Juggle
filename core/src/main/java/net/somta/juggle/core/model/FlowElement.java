package net.somta.juggle.core.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.model.node.ConditionNode;
import net.somta.juggle.core.model.node.EndNode;
import net.somta.juggle.core.model.node.MethodNode;
import net.somta.juggle.core.model.node.StartNode;

/**
 * 流程元素类
 * @author husong
 * @date 2022/12/15
 **/
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "elementType",
        visible = true)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = StartNode.class, name = "START"),
        @JsonSubTypes.Type(value = EndNode.class, name = "END"),
        @JsonSubTypes.Type(value = MethodNode.class, name = "METHOD"),
        @JsonSubTypes.Type(value = ConditionNode.class, name = "CONDITION"),
})
public class FlowElement {
    /**
     * 流程内元素唯一标识
     */
    private String key;
    /**
     * 流程元素名称
     */
    private String name;
    /**
     * 流程元素类型
     */
    private ElementTypeEnum elementType;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElementTypeEnum getElementType() {
        return elementType;
    }

    public void setElementType(ElementTypeEnum elementType) {
        this.elementType = elementType;
    }
}
