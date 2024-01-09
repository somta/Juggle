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

package net.somta.juggle.core.dispatcher.impl;

import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.WorkRunnerImpl;
import net.somta.juggle.core.dispatcher.AbstractDispatcher;
import net.somta.juggle.core.enums.FlowStatusEnum;
import net.somta.juggle.core.executor.FlowExecutor;
import net.somta.juggle.core.model.FlowResult;

/**
 * 默认基于内存的调度器
 * @author husong
 */
public class AsyncDispatcher extends AbstractDispatcher {

    private FlowExecutor flowExecutor;

    public AsyncDispatcher() {
        super(new WorkRunnerImpl().startWork());
        flowExecutor = new FlowExecutor();
    }

    @Override
    protected FlowResult doSend(FlowRuntimeContext flowRuntimeContext) {
        super.workRunner.postWork(() -> startFlow(flowRuntimeContext) );
        return new FlowResult().setStatus(FlowStatusEnum.INIT);
    }

    /**
     * 开始执行流程
     * @param flowRuntimeContext
     */
    private void startFlow(FlowRuntimeContext flowRuntimeContext){
        System.out.println("startFlow.......");
        flowExecutor.execute(flowRuntimeContext);
    }
}
