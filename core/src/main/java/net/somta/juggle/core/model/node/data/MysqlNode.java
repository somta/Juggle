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
package net.somta.juggle.core.model.node.data;

import net.somta.juggle.core.model.FillStruct;
import net.somta.juggle.core.model.node.FlowNode;

/**
 * @author husong
 */
public class MysqlNode extends FlowNode {

    private OperationType operationType;
    private String sql;
    // 输入的结果对象的定义  可以是一个list  可以是一个对象，可以是一个基础类型 应该是把结果输出给某个变量
    //这里定义的应该是一个变量key
    private String output;

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public enum OperationType {
        CHANGE,QUERY
    }

}
