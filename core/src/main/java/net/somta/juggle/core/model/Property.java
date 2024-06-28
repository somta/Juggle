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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author husong
 */
public class Property {
    /**
     * 属性key
     */
    private String propKey;
    /**
     * 属性名称
     */
    private String propName;

    /**
     * 参数的数据类型
     */
    private DataType dataType;

    // todo 这里能否直接反序列化成对象
    //private DataType dataTypeObj;

    public String getPropKey() {
        return propKey;
    }

    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    /*public DataType getDataTypeObj() {
        return dataTypeObj;
    }

    public void setDataTypeObj(DataType dataTypeObj) {
        this.dataTypeObj = dataTypeObj;
    }*/
}
