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
/**
 * 抽象的元素节点校验器
 *
 * @author husong
 * @since 1.0.0
 */
public abstract class AbstractElementValidator implements IValidator {

    @Override
    public Boolean validateFlow(FlowElement flowElement) {
        doValidateIncoming(flowElement);
        doValidateOutgoing(flowElement);
        return true;
    }

    protected abstract void doValidateIncoming(FlowElement flowElement);

    protected abstract void doValidateOutgoing(FlowElement flowElement);

}
