package net.somta.juggle.console.interfaces.param.suite;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

/**
 * swagger数据参数
 *
 * @author chen shuai
 * @date 2025/10/11 16:39
 */
@Schema(description = "swagger数据参数")
public class SwaggerParam {
    /**
     * swagger的原始数据，可能是单个接口数据，也可能是多个接口数据
     */
    @Schema(description = "swagger的原始数据，可能是单个接口数据，也可能是多个接口数据", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "swagger数据不能为空")
    private String swaggerData;

    public String getSwaggerData() {
        return swaggerData;
    }

    public void setSwaggerData(String swaggerData) {
        this.swaggerData = swaggerData;
    }

    @Override
    public String toString() {
        return "SwaggerParam{" +
                "swaggerData='" + swaggerData + '\'' +
                '}';
    }
}
