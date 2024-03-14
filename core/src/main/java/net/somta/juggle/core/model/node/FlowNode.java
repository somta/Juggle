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

package net.somta.juggle.core.model.node;

import net.somta.juggle.core.model.FlowElement;

import java.util.List;

/**
 * 流程节点类
 * @author husong
 * @since 1.0.0
 **/
public class FlowNode extends FlowElement {
    private String desc;
    /**
     * 节点入口
     */
    private List<String> incomings;
    /**
     * 节点出口
     */
    private List<String> outgoings;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getIncomings() {
        return incomings;
    }

    public void setIncomings(List<String> incomings) {
        this.incomings = incomings;
    }

    public List<String> getOutgoings() {
        return outgoings;
    }

    public void setOutgoings(List<String> outgoings) {
        this.outgoings = outgoings;
    }
}
