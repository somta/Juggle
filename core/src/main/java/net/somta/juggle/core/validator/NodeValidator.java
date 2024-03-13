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

import net.somta.juggle.core.enums.ElementTypeEnum;
import net.somta.juggle.core.model.FlowElement;
import net.somta.juggle.core.model.node.FlowNode;

/**
 * @author husong
 * @since 1.0.0
 */
public class NodeValidator {

    public void validateNode(FlowElement flowElement){
        IValidator validator = getElementValidator(flowElement.getElementType());
        if(validator != null){
            validator.validateFlow(flowElement);
        }
    }

    private IValidator getElementValidator(ElementTypeEnum flowElementType){
        switch (flowElementType) {
            case START: return new StartNodeValidator();
            case END: return new EndNodeValidator();
            case METHOD: return new MethodNodeValidator();
            case CONDITION: return new ConditionNodeValidator();
            default: return null;
        }
    }
}
