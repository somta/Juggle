package net.somta.juggle.console.domain.parameter;

import io.swagger.v3.oas.models.media.Schema;
import net.somta.juggle.core.enums.DataTypeEnum;

/**
 * 数据类型转换
 *
 * @author chen shuai
 * @date 2025/10/22 17:13
 */
public class DataTypeUtil {

    public static DataTypeEnum from(String type) {
        if ("string".equalsIgnoreCase(type)) {
            return DataTypeEnum.String;
        }
        if ("integer".equalsIgnoreCase(type)) {
            return DataTypeEnum.Integer;
        }
        if ("number".equalsIgnoreCase(type)) {
            return DataTypeEnum.Double;
        }
        if ("boolean".equalsIgnoreCase(type)) {
            return DataTypeEnum.Boolean;
        }
        if ("object".equalsIgnoreCase(type)) {
            return DataTypeEnum.Object;
        }
        if ("array".equalsIgnoreCase(type)) {
            return DataTypeEnum.List;
        }
        return null;
    }

    public static boolean isBasicType(DataTypeEnum dataType) {
        return !DataTypeEnum.Object.equals(dataType) && !DataTypeEnum.List.equals(dataType);
    }

    public static boolean isObject(DataTypeEnum dataType) {
        return DataTypeEnum.Object.equals(dataType);
    }

    public static boolean isList(DataTypeEnum dataType) {
        return DataTypeEnum.List.equals(dataType);
    }


    /**
     * 带format的类型转换,后续可能会用到，暂时先保留该方法
     *
     * @param s schema
     * @return 类型枚举
     */
    public static DataTypeEnum from(Schema s) {
        String type = resolveSchemaType(s);
        String format = s.getFormat();
        if ("string".equalsIgnoreCase(type)) {
            return DataTypeEnum.String;
        }
        if ("integer".equalsIgnoreCase(type) && "int32".equalsIgnoreCase(format)) {
            return DataTypeEnum.Integer;
        }
        if ("integer".equalsIgnoreCase(type) && "int64".equalsIgnoreCase(format)) {
            return DataTypeEnum.Integer;
        }
        if ("number".equalsIgnoreCase(type) && "float".equalsIgnoreCase(format)) {
            return DataTypeEnum.Double;
        }
        if ("number".equalsIgnoreCase(type) && "double".equalsIgnoreCase(format)) {
            return DataTypeEnum.Double;
        }
        if ("boolean".equalsIgnoreCase(type)) {
            return DataTypeEnum.Boolean;
        }
        if ("array".equalsIgnoreCase(type)) {
            return DataTypeEnum.List;
        }
        if ("object".equalsIgnoreCase(type)) {
            return DataTypeEnum.Object;
        }
        return null;
    }

    public static String resolveSchemaType(Schema s) {
        if (s.getType() == null) {
            if (s.get$ref() != null) {
                return DataTypeEnum.Object.name().toLowerCase();
            }
        }
        return s.getType();
    }
}
