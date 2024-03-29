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

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.model.node.CodeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * code executor
 *
 * @author husong
 * @since 1.1.0
 */
public class CodeNodeExecutor extends AbstractElementExecutor{
    private final static Logger logger = LoggerFactory.getLogger(CodeNodeExecutor.class);
    @Override
    protected void doPreExecute(FlowRuntimeContext flowRuntimeContext) {

    }

    @Override
    protected void doExecute(FlowRuntimeContext flowRuntimeContext) {
        CodeNode codeNode = (CodeNode) flowRuntimeContext.getCurrentNode();
        Binding binding = new Binding();
        binding.setVariable("$var", flowRuntimeContext.getVariableManager());
        GroovyShell shell = new GroovyShell(binding);
        Object result = shell.evaluate(codeNode.getContent());
        logger.debug("Script result: " + result);
    }

    @Override
    protected void doPostExecute(FlowRuntimeContext flowRuntimeContext) {
        CodeNode codeNode = (CodeNode) flowRuntimeContext.getCurrentNode();
        String nextNodeKey = codeNode.getOutgoings().get(0);
        logger.debug("方法节点执行器完毕，下一个节点的KEY为：{}", nextNodeKey);
        super.fillNextNode(flowRuntimeContext,nextNodeKey);
    }
}
