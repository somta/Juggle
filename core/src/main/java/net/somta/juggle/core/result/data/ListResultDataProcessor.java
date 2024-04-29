package net.somta.juggle.core.result.data;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Property;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.variable.AbstractVariableManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListResultDataProcessor extends AbstractResultDataProcessor {
    public ListResultDataProcessor(AbstractVariableManager variableManager) {
        super(variableManager);
    }

    @Override
    protected Object getFillResultData(ResultSet resultSet, Variable variable) throws SQLException {
        List<Map<String,Object>> resultList = new ArrayList<>();
        if(DataTypeEnum.Object.name().equals(variable.getDataType().getItemType())){
            String schema = variable.getDataType().getStructureSchema();
            List<Property> propertyList = JsonSerializeHelper.deserialize(schema,List.class, Property.class);
            propertyList.forEach(property -> property.setDataTypeObj(JsonSerializeHelper.deserialize(property.getDataType(), DataType.class)));
            Map<String,Object> result = null;
            while (resultSet.next()) {
                result = new HashMap<>(16);
                for (Property property : propertyList) {
                    Object columnValue = super.getResultValue(resultSet,property);
                    result.put(property.getPropKey(),columnValue);
                }
                resultList.add(result);
            }
        }
        return resultList;
    }
}
