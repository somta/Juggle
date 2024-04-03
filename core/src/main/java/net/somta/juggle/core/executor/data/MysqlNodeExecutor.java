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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.FlowRuntimeContext;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.executor.AbstractElementExecutor;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Property;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.model.node.CodeNode;
import net.somta.juggle.core.model.node.data.MysqlNode;
import net.somta.juggle.core.variable.AbstractVariableManager;
import net.somta.juggle.core.variable.MemoryVariableManager;

import javax.sql.DataSource;
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
 */
public class MysqlNodeExecutor extends AbstractElementExecutor {
    @Override
    protected void doPreExecute(FlowRuntimeContext flowRuntimeContext) {

    }

    @Override
    protected void doExecute(FlowRuntimeContext flowRuntimeContext) {
        MysqlNode mysqlNode = (MysqlNode) flowRuntimeContext.getCurrentNode();

        DataSource dataSource = getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM my_table");
            handleResultSet(rs,mysqlNode.getOutput(),flowRuntimeContext.getVariableManager());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPostExecute(FlowRuntimeContext flowRuntimeContext) {

    }

    private String handleSql(){

        return null;
    }

    private static void handleResultSet(ResultSet resultSet,String outputKey, AbstractVariableManager variableManager) throws SQLException {
        Variable variable = variableManager.getVariableSchema(outputKey);
        DataTypeEnum dataTypeEnum = variable.getDataType().getType();
        if(DataTypeEnum.Object.equals(dataTypeEnum)){
            Map<String,Object> result = new HashMap<>(16);
            String schema = variable.getDataType().getStructureSchema();
            List<Property> propertyList = JsonSerializeHelper.deserialize(schema,List.class, Property.class);
            propertyList.forEach(property -> property.setDataTypeObj(JsonSerializeHelper.deserialize(property.getDataType(),DataType.class)));
            while (resultSet.next()) {
                for (Property property : propertyList) {
                    Object columnValue = getResultValue(resultSet,property);
                    result.put(property.getPropKey(),columnValue);
                }
            }
            //赋值结果到变量
            variableManager.setVariableValue(outputKey,result);
        }else if(DataTypeEnum.List.equals(dataTypeEnum)){
            List<Map<String,Object>> resultList = new ArrayList<>();
            if(DataTypeEnum.Object.name().equals(variable.getDataType().getItemType())){
                String schema = variable.getDataType().getStructureSchema();
                List<Property> propertyList = JsonSerializeHelper.deserialize(schema,List.class, Property.class);
                propertyList.forEach(property -> property.setDataTypeObj(JsonSerializeHelper.deserialize(property.getDataType(),DataType.class)));
                Map<String,Object> result = null;
                while (resultSet.next()) {
                    result = new HashMap<>(16);
                    for (Property property : propertyList) {
                        Object columnValue = getResultValue(resultSet,property);
                        result.put(property.getPropKey(),columnValue);
                    }
                    resultList.add(result);
                }
            }
            variableManager.setVariableValue(outputKey,resultList);
        }

    }

    /**
     * 解析返回sql中的变量
     * @param sql  SELECT * FROM my_table WHERE id=${id} AND name=${userName}
     * @return id userName
     */
    public List<String> extractVariables(String sql) {
        Pattern pattern = Pattern.compile("\\$\\{([^}]*)\\}");
        Matcher matcher = pattern.matcher(sql);
        List<String> variables = new ArrayList<>();
        while (matcher.find()) {
            variables.add(matcher.group(1));
        }
        return variables;
    }

    private static Object getResultValue(ResultSet resultSet, Property property) throws SQLException {
        DataType dataType = property.getDataTypeObj();
        switch (dataType.getType()){
            case String:
                return resultSet.getString(property.getPropKey());
            case Integer:
                return resultSet.getInt(property.getPropKey());
            case Double:
                return resultSet.getDouble(property.getPropKey());
            default:
                return null;
        }
    }

    private static DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://rm-bp129qaj9mr99z9848o.mysql.rds.aliyuncs.com:3306/juggle?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false");
        config.setUsername("juggle");
        config.setPassword("juggle#123");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    public static void main(String[] args) {
        Map<String, Variable> variableSchemaMap = new HashMap<>();
        Variable variable = new Variable();
        variable.setKey("env_api");
        variable.setName("api对象变量");
        DataType dataType = new DataType();
        dataType.setType(DataTypeEnum.Object);
        dataType.setStructureSchema("[{\"propKey\":\"id\",\"propName\":\"id\",\"dataType\":\"{\\\"type\\\":\\\"Integer\\\",\\\"itemType\\\":\\\"\\\",\\\"objectKey\\\":null,\\\"objectStructure\\\":null}\"},{\"propKey\":\"apiName\",\"propName\":\"apiName\",\"dataType\":\"{\\\"type\\\":\\\"String\\\",\\\"itemType\\\":\\\"\\\",\\\"objectKey\\\":null,\\\"objectStructure\\\":null}\"}]");
        variable.setDataType(dataType);
        variableSchemaMap.put("env_api",variable);

        AbstractVariableManager variableManager = new MemoryVariableManager(variableSchemaMap);
        DataSource dataSource = getDataSource();

        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id as id, api_name as apiName FROM t_api");
            handleResultSet(rs,"env_api",variableManager);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
