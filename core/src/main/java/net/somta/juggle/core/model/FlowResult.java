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

import net.somta.juggle.core.enums.FlowStatusEnum;

import java.util.Map;

/**
 * @author husong
 * @since 1.0.0
 */
public class FlowResult {
    private String flowInstanceId;
    private FlowStatusEnum status;
    private Map<String,Object> data;

    public String getFlowInstanceId() {
        return flowInstanceId;
    }

    public FlowResult setFlowInstanceId(String flowInstanceId) {
        this.flowInstanceId = flowInstanceId;
        return this;
    }

    public FlowStatusEnum getStatus() {
        return status;
    }

    public FlowResult setStatus(FlowStatusEnum status) {
        this.status = status;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public FlowResult setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }
}
