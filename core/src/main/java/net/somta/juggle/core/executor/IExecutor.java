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
package net.somta.juggle.core.executor;

import net.somta.juggle.core.FlowRuntimeContext;


/**
 * @author husong
 * @since 1.0.0
 */
public interface IExecutor {

    /**
     * 执行流程
     * @param flowRuntimeContext
     */
    void execute(FlowRuntimeContext flowRuntimeContext);

    /**
     * 获取下一个节点元素的执行器
     * @param flowRuntimeContext
     * @return
     */
    IExecutor getNextNodeExecutor(FlowRuntimeContext flowRuntimeContext);
}
