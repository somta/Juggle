package net.somta.juggle.core.result.data;

import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Property;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.variable.AbstractVariableManager;

import java.sql.ResultSet;
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

    protected abstract Object getFillResultData(ResultSet resultSet, Variable variable) throws SQLException;

}
