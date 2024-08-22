/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/

package net.somta.juggle.core.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.model.node.*;
import net.somta.juggle.core.model.node.data.MysqlNode;

/**
 * 流程元素类
 * @author husong
 * @since 1.0.0
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
        @JsonSubTypes.Type(value = AssignNode.class, name = "ASSIGN"),
        @JsonSubTypes.Type(value = CodeNode.class, name = "CODE"),
        @JsonSubTypes.Type(value = MysqlNode.class, name = "MYSQL"),
        @JsonSubTypes.Type(value = OpenAiNode.class, name = "AI"),
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
