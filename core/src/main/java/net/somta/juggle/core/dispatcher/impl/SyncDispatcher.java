/*
Copyright (C) 2023-2024 husong

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

package net.somta.juggle.core.dispatcher.impl;

import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.dispatcher.AbstractDispatcher;
import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.executor.FlowExecutor;
import net.somta.juggle.core.model.FlowResult;

import java.util.Map;

/**
 * @author husong
 */
public class SyncDispatcher extends AbstractDispatcher {

    private FlowExecutor flowExecutor;

    public SyncDispatcher() {
        super(null);
        flowExecutor = new FlowExecutor();
    }

    @Override
    protected FlowResult doSend(FlowRuntimeContext flowRuntimeContext) {
        Map<String,Object> data = flowExecutor.execute(flowRuntimeContext);
        FlowResult flowResult = new FlowResult()
                .setStatus(flowRuntimeContext.getFlowStatus())
                .setData(data);
        return flowResult;
    }
}
