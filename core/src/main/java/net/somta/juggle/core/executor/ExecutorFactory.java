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

import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.executor.data.MysqlNodeExecutor;
import net.somta.juggle.core.model.FlowElement;

/**
 * 执行器工厂
 *
 * @author husong
 * @since 1.0.0
 */
public class ExecutorFactory {

    /**
     * 根据节点类型返回对应节点的执行器
     * @param flowElement
     * @return
     */
    public static AbstractElementExecutor getElementExecutor(FlowElement flowElement) {
        if(flowElement == null){
            return null;
        }
        AbstractElementExecutor abstractElementExecutor = getElementExecutor(flowElement.getElementType());
        return abstractElementExecutor;
    }

    /**
     * 获取元素的执行器
     * @param flowElementType
     * @return
     */
    private static AbstractElementExecutor getElementExecutor(ElementTypeEnum flowElementType) {
        switch (flowElementType) {
            case START: return new StartNodeExecutor();
            case END: return new EndNodeExecutor();
            case METHOD: return new MethodNodeExecutor();
            case CONDITION: return new ConditionNodeExecutor();
            case ASSIGN: return new AssignNodeExecutor();
            case CODE: return new CodeNodeExecutor();
            case MYSQL: return new MysqlNodeExecutor();
            case AI: return new OpenAiNodeExecutor();
            default: return null;
        }
    }

}
