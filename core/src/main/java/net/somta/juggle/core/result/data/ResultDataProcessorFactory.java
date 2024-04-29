package net.somta.juggle.core.result.data;

import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.variable.AbstractVariableManager;

public class ResultDataProcessorFactory {

    public static AbstractResultDataProcessor getDataResultProcessor(String outputVariableKey, AbstractVariableManager variableManager){
        Variable variable = variableManager.getVariableSchema(outputVariableKey);
        DataTypeEnum dataTypeEnum = variable.getDataType().getType();
        if(DataTypeEnum.Object.equals(dataTypeEnum)){
            return new ObjectResultDataProcessor(variableManager);
        } else if (DataTypeEnum.List.equals(dataTypeEnum)) {
            return new ListResultDataProcessor(variableManager);
        }
        throw new RuntimeException("不支持的数据类型");
    }
}
