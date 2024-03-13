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

import net.somta.juggle.core.enums.FieldSourceEnum;

/**
 * @author husong
 * @since 1.0.0
 */
public class FillStruct {
    private String source;

    private FieldSourceEnum sourceType;

    private String sourceDataType;

    private String target;

    private FieldSourceEnum targetType;

    private String targetDataType;


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public FieldSourceEnum getSourceType() {
        return sourceType;
    }

    public void setSourceType(FieldSourceEnum sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceDataType() {
        return sourceDataType;
    }

    public void setSourceDataType(String sourceDataType) {
        this.sourceDataType = sourceDataType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public FieldSourceEnum getTargetType() {
        return targetType;
    }

    public void setTargetType(FieldSourceEnum targetType) {
        this.targetType = targetType;
    }

    public String getTargetDataType() {
        return targetDataType;
    }

    public void setTargetDataType(String targetDataType) {
        this.targetDataType = targetDataType;
    }
}
