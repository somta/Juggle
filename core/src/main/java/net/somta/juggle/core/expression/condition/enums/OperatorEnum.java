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
package net.somta.juggle.core.expression.condition.enums;


/**
 * @author husong
 * @since 1.0.0
 */
public enum OperatorEnum {
    EQUAL("equal","等于"),
    NOT_EQUAL("notEqual","不等于"),
    EMPTY("empty","为空"),
    NOT_EMPTY("notEmpty","不为空"),
    CONTAINS("contains","包含"),
    NOT_CONTAINS("notContains","不包含"),
    GREATER_THAN("greaterThan","大于"),
    GREATER_THAN_OR_EQUAL("greaterThanOrEqual","大于等于"),
    LESS_THAN("lessThan","小于"),
    LESS_THAN_OR_EQUAL("lessThanOrEqual","小于等于");

    private String code;
    private String desc;

    OperatorEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
    public static OperatorEnum getByCode(String code){
        for (OperatorEnum operator : OperatorEnum.values()) {
            if (operator.getCode().equals(code)) {
                return operator;
            }
        }
        return null;
    }
}
