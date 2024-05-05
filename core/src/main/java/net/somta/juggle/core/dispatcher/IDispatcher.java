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
package net.somta.juggle.core.dispatcher;

import net.somta.juggle.core.executor.data.IDataSource;
import net.somta.juggle.core.model.Flow;
import net.somta.juggle.core.model.FlowResult;
import net.somta.juggle.core.result.IFlowResultManager;

import java.util.Map;

/**
 * 调度器接口
 * @author husong
 */
public interface IDispatcher {

    /**
     * 分发流程
     * @param flow
     * @param flowData
     * @return
     */
    FlowResult doDispatcher(Flow flow, Map<String,Object> flowData, IFlowResultManager flowResultManager, IDataSource dataSourceManager);

}
