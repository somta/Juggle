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

import net.somta.juggle.core.enums.DataTypeEnum;

/**
 * 数据类型描述类
 *
 * @author husong
 * @since 1.0.0
 */
public class DataType {

    /**
     * 数据类型
     */
    private DataTypeEnum type;
    /**
     * 集合的泛型类型
     */
    private String itemType;

    /**
     * 对象的结构
     */
    private String objectStructure;

    public DataType() {
    }

    public DataType(DataTypeEnum type) {
        this.type = type;
    }

    public DataTypeEnum getType() {
        return type;
    }

    public void setType(DataTypeEnum type) {
        this.type = type;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getObjectStructure() {
        return objectStructure;
    }

    public void setObjectStructure(String objectStructure) {
        this.objectStructure = objectStructure;
    }
}
