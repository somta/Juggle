package net.somta.juggle.core.result.data;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Property;
import net.somta.juggle.core.model.Variable;
import net.somta.juggle.core.variable.AbstractVariableManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectResultDataProcessor extends AbstractResultDataProcessor {
    public ObjectResultDataProcessor(AbstractVariableManager variableManager) {
        super(variableManager);
    }

    @Override
    protected Object getFillResultData(ResultSet resultSet, Variable variable) throws SQLException {
        Map<String,Object> result = new HashMap<>(16);
        List<Property> propertyList = variable.getDataType().getObjectStructure();
        //List<Property> propertyList = JsonSerializeHelper.deserialize(schema,List.class, Property.class);
        //propertyList.forEach(property -> property.setDataTypeObj(JsonSerializeHelper.deserialize(property.getDataType(), DataType.class)));
        while (resultSet.next()) {
            for (Property property : propertyList) {
                Object columnValue = getResultValue(resultSet,property);
                result.put(property.getPropKey(),columnValue);
            }
        }
        return result;
    }
}
