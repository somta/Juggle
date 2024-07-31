package net.somta.juggle.core.result.data;

import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Property;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.variable.AbstractVariableManager;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public abstract class AbstractResultDataProcessor {

    private AbstractVariableManager variableManager;

    public AbstractResultDataProcessor(AbstractVariableManager variableManager) {
        this.variableManager = variableManager;
    }

    public void fillDataResultToVariable(ResultSet resultSet,String outputVariableKey) {
        Variable variable = this.variableManager.getVariableSchema(outputVariableKey);
        Object result = null;
        try {
            result = getFillResultData(resultSet,variable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //赋值结果到变量
        this.variableManager.setVariableValue(outputVariableKey,result);
    }

    protected static Object getResultValue(ResultSet resultSet, Property property) throws SQLException {
        DataType dataType = property.getDataType();
        if(!isColumnExist(resultSet,property.getPropKey())){
            return null;
        }
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

    /**
     * Determine if there is a column in the returned dataset that corresponds to the attribute key of the receiving object
     * @param resultSet Query the returned dataset object
     * @param propKey The key of the receiving object
     * @return
     * @throws SQLException
     */
    private static boolean isColumnExist(ResultSet resultSet, String propKey) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        if(columnCount <= 0){
            return false;
        }
        for (int i=1; i<=columnCount; i++){
            String columnName = metaData.getColumnLabel(i);
            if(StringUtils.isNotEmpty(columnName) && columnName.equals(propKey)){
                return true;
            }
        }
        return false;
    }

    protected abstract Object getFillResultData(ResultSet resultSet, Variable variable) throws SQLException;

}
