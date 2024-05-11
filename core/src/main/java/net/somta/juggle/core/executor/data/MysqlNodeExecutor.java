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
package net.somta.juggle.core.executor.data;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.executor.AbstractElementExecutor;
import net.somta.juggle.core.model.node.data.MysqlNode;
import net.somta.juggle.core.result.data.AbstractResultDataProcessor;
import net.somta.juggle.core.result.data.ResultDataProcessorFactory;
import net.somta.juggle.core.variable.AbstractVariableManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author husong
 * @since 1.2.0
 */
public class MysqlNodeExecutor extends AbstractElementExecutor {

    private final static Logger logger = LoggerFactory.getLogger(MysqlNodeExecutor.class);

    @Override
    protected void doPreExecute(FlowRuntimeContext flowRuntimeContext) {

    }

    @Override
    protected void doExecute(FlowRuntimeContext flowRuntimeContext) {
        MysqlNode mysqlNode = (MysqlNode) flowRuntimeContext.getCurrentNode();
        String realSql = handleSql(mysqlNode.getSql(),flowRuntimeContext.getVariableManager());
        DataSource dataSource = (DataSource) flowRuntimeContext.getDataSourceManager().getDataSource(mysqlNode.getDataSourceId());
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(realSql);
            AbstractResultDataProcessor dataResultProcessor = ResultDataProcessorFactory.getDataResultProcessor(mysqlNode.getVariableKey(),flowRuntimeContext.getVariableManager());
            dataResultProcessor.fillDataResultToVariable(rs,mysqlNode.getVariableKey());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPostExecute(FlowRuntimeContext flowRuntimeContext) {
        MysqlNode mysqlNode = (MysqlNode) flowRuntimeContext.getCurrentNode();
        String nextNodeKey = mysqlNode.getOutgoings().get(0);
        logger.debug("MySql节点执行器完毕，下一个节点的KEY为：{}", nextNodeKey);
        super.fillNextNode(flowRuntimeContext,nextNodeKey);
    }

    /**
     * Handling variables in SQL
     * @param sql
     * @param variableManager
     * @return real sql string
     */
    private static String handleSql(String sql, AbstractVariableManager variableManager){
        List<String> variableList = parseSqlVariables(sql);
        Map<String,Object> templateEnvMap = new HashMap<>(16);
        for (String variable : variableList){
            Object variableValue = variableManager.getVariableValue(variable);
            templateEnvMap.put(variable,variableValue);
        }
        String realSql = sql;
        try {
            Configuration conf = new Configuration(Configuration.VERSION_2_3_32);
            conf.setTemplateLoader(new StringTemplateLoader());
            Template template = new Template("sqlTpl", sql, conf);
            StringWriter result = new StringWriter();
            template.process(templateEnvMap, result);
            realSql = result.toString();
            logger.debug("The processed real SQL: {}",realSql);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
        return realSql;
    }

    /**
     * 解析返回sql中的变量
     * @param sql  SELECT * FROM my_table WHERE id=${id} AND name=${userName}
     * @return id userName
     */
    public static List<String> parseSqlVariables(String sql) {
        Pattern pattern = Pattern.compile("\\$\\{([^}]*)\\}");
        Matcher matcher = pattern.matcher(sql);
        List<String> variables = new ArrayList<>();
        while (matcher.find()) {
            variables.add(matcher.group(1));
        }
        return variables;
    }
}
