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

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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
        if(CodeNode.LanguageType.groovy.equals(codeNode.getLanguage())){
            Binding binding = new Binding();
            binding.setVariable("$var", flowRuntimeContext.getVariableManager());
            GroovyShell shell = new GroovyShell(binding);
            Object result = shell.evaluate(codeNode.getContent());
            logger.debug("Groovy Script result: " + result);
        } else if (CodeNode.LanguageType.javascript.equals(codeNode.getLanguage())) {
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
            nashorn.put("console", new Console());
            nashorn.getBindings(ScriptContext.GLOBAL_SCOPE).put("$var", flowRuntimeContext.getVariableManager());
            try {
                Object result = nashorn.eval(codeNode.getContent());
                logger.debug("JavaScript Script result: " + result);
            }catch(ScriptException e){
                System.out.println("执行脚本错误: "+ e.getMessage());
            }
        }else {
            throw new IllegalArgumentException("不支持"+codeNode.getLanguage()+"语言");
        }
    }

    @Override
    protected void doPostExecute(FlowRuntimeContext flowRuntimeContext) {
        CodeNode codeNode = (CodeNode) flowRuntimeContext.getCurrentNode();
        String nextNodeKey = codeNode.getOutgoings().get(0);
        logger.debug("方法节点执行器完毕，下一个节点的KEY为：{}", nextNodeKey);
        super.fillNextNode(flowRuntimeContext,nextNodeKey);
    }

    public static class Console {
        public void log(Object... objects) {
            for (Object obj : objects) {
                logger.info(obj + " ");
            }
        }
    }
}
