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

package net.somta.juggle.core.result;

import org.apache.commons.collections4.map.LRUMap;

import java.util.Map;

/**
 * @author husong
 * @since 1.0.0
 */
public class MemoryFlowResultManager implements IFlowResultManager {

    private LRUMap<String, Map<String,Object>> results = new LRUMap<>(2000,500);

    @Override
    public boolean putFlowResult(String flowInstanceId,Map<String,Object> resultData) {
        results.put(flowInstanceId,resultData);
        return true;
    }

    @Override
    public Map<String, Object> getFlowResult(String flowInstanceId) {
        return results.get(flowInstanceId);
    }
}
