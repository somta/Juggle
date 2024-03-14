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
package net.somta.juggle.core.enums;

/**
 * @author husong
 * @since 1.0.0
 */
public enum RequestContentTypeEnum {

    APPLICATION_JSON("application/json"),
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded");

    private String value;

    RequestContentTypeEnum(String value) {
        this.value = value;
    }

    public static RequestContentTypeEnum findEnumByValue(String value){
        for (RequestContentTypeEnum e : RequestContentTypeEnum.values()) {
            if(e.getValue().equals(value)){
                return e;
            }
        }
        throw new IllegalArgumentException("requestContentType is not support or requestContentType is null!");
    }

    public String getValue() {
        return value;
    }
}
