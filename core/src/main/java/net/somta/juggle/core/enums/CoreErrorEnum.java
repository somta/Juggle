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
public enum CoreErrorEnum {

    ENV_KEY_ERROR(1001, "变量的key不能为空"),
    FLOW_ELEMENT_IS_EMPTY_ERROR(1002, "流程元素不能为空"),
    FLOW_NOT_EXIST_START_NODE_ERROR(1003, "流程不存在开始节点"),
    ASSIGN_NODE_ASSIGN_TYPE_ERROR(1004, "赋值节点不支持该赋值类型"),
    ;


    private int errCode;
    private String errMsg;

    CoreErrorEnum(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
