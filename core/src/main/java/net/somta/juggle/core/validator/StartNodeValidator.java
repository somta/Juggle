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

package net.somta.juggle.core.validator;

import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.node.FlowNode;
import net.somta.juggle.core.model.node.StartNode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author husong
 * @since 1.0.0
 */
public class StartNodeValidator extends AbstractElementValidator{
    @Override
    protected void doValidateIncoming(FlowElement flowElement) {

    }

    @Override
    protected void doValidateOutgoing(FlowElement flowElement) {
        StartNode startNode = (StartNode)flowElement;
        List<String> outgoings = startNode.getOutgoings();
        if(CollectionUtils.isEmpty(outgoings) || (CollectionUtils.isNotEmpty(outgoings) && outgoings.size() > 1)){
            // todo 这里要抛出一个异常
        }
    }
}
