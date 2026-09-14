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
package com.matecoder.juggle.console.application.service.suite.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import com.matecoder.juggle.console.application.assembler.suite.IApiAssembler;
import com.matecoder.juggle.console.domain.parameter.DataTypeUtil;
import com.matecoder.juggle.console.domain.parameter.vo.InputParameterVO;
import com.matecoder.juggle.console.domain.parameter.vo.OutputParameterVO;
import com.matecoder.juggle.console.domain.suite.api.ApiAO;
import com.matecoder.juggle.console.domain.suite.api.repository.IApiRepository;
import com.matecoder.juggle.console.domain.suite.api.vo.ApiVO;
import com.matecoder.juggle.console.domain.suite.suiteinfo.repository.ISuiteRepository;
import com.matecoder.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import com.matecoder.juggle.console.interfaces.dto.suite.ApiInfoDTO;
import com.matecoder.juggle.console.interfaces.dto.suite.ApiDTO;
import com.matecoder.juggle.console.application.service.suite.IApiService;
import com.matecoder.juggle.console.interfaces.param.suite.ApiAddParam;
import com.matecoder.juggle.console.interfaces.param.suite.ApiDebugParam;
import com.matecoder.juggle.console.interfaces.param.suite.ApiQueryParam;
import com.matecoder.juggle.console.interfaces.param.suite.ApiUpdateParam;
import com.matecoder.juggle.core.enums.DataTypeEnum;
import com.matecoder.juggle.core.enums.ParameterPositionEnum;
import com.matecoder.juggle.core.enums.RequestContentTypeEnum;
import com.matecoder.juggle.core.enums.RequestTypeEnum;
import com.matecoder.juggle.core.http.HttpClientFactory;
import com.matecoder.juggle.core.http.IHttpClient;
import com.matecoder.juggle.core.http.Request;
import com.matecoder.juggle.core.model.DataType;
import com.matecoder.juggle.core.model.Property;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author husong
 * @since 1.0.0
 */
@Service
public class ApiServiceImpl implements IApiService {

    private final IApiRepository apiRepository;
    private final ISuiteRepository suiteRepository;

    public ApiServiceImpl(IApiRepository apiRepository, ISuiteRepository suiteRepository) {
        this.apiRepository = apiRepository;
        this.suiteRepository = suiteRepository;
    }

    @Override
    public Boolean addApi(ApiAddParam apiAddParam) {
        ApiAO apiAo = IApiAssembler.IMPL.paramToAo(apiAddParam);
        apiAo.initApiCode();
        apiAo.initParameterList(apiAddParam.getApiInputParams(), apiAddParam.getApiOutputParams());
        apiAo.initHeaderList(apiAddParam.getApiHeaders());
        return apiRepository.addApi(apiAo);
    }

    @Override
    public Boolean deleteApi(Long apiId) {
        return apiRepository.deleteApi(apiId);
    }

    @Override
    public Boolean updateApi(ApiUpdateParam apiUpdateParam) {
        ApiAO apiAo = IApiAssembler.IMPL.paramToAo(apiUpdateParam);
        apiAo.initApiCode();
        apiAo.initParameterList(apiUpdateParam.getApiInputParams(), apiUpdateParam.getApiOutputParams());
        apiAo.initHeaderList(apiUpdateParam.getApiHeaders());
        return apiRepository.updateApi(apiAo);
    }

    @Override
    public ApiInfoDTO getApiInfo(Long apiId) {
        ApiAO apiAo = apiRepository.queryApi(apiId);
        ApiInfoDTO apiInfoDto = IApiAssembler.IMPL.aoToDto(apiAo);
        SuiteVO suiteVo = suiteRepository.querySuiteById(apiAo.getSuiteId());
        apiInfoDto.setSuiteFlag(suiteVo.getSuiteFlag());
        return apiInfoDto;
    }

    @Override
    public ApiInfoDTO getApiInfoByCode(String apiCode) {
        ApiAO apiAo = apiRepository.queryApiByCode(apiCode);
        ApiInfoDTO apiInfoDto = IApiAssembler.IMPL.aoToDto(apiAo);
        return apiInfoDto;
    }

    @Override
    public List<ApiDTO> getApiListBySuiteId(Long suiteId) {
        List<ApiVO> apiVoList = apiRepository.getApiListBySuiteId(suiteId);
        List<ApiDTO> apiDtoList = IApiAssembler.IMPL.voListToDtoList(apiVoList);
        return apiDtoList;
    }

    @Override
    public List<ApiDTO> getApiListBySuiteCode(String suiteCode) {
        List<ApiVO> apiVoList = apiRepository.getApiListBySuiteCode(suiteCode);
        return IApiAssembler.IMPL.voListToDtoList(apiVoList);
    }

    @Override
    public PageInfo getApiPageList(ApiQueryParam apiQueryParam) {
        Page<ApiDTO> page = PageHelper.startPage(apiQueryParam.getPageNum(), apiQueryParam.getPageSize());
        List<ApiVO> apiVoList = apiRepository.queryApiPageList(apiQueryParam);
        List<ApiDTO> apiList = IApiAssembler.IMPL.voListToDtoList(apiVoList);
        PageInfo pageInfo = new PageInfo(apiList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public Map<String, Object> debugApi(Long apiId, ApiDebugParam apiDebugParam) {
        ApiAO apiAo = apiRepository.queryApi(apiId);
        IHttpClient httpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.findEnumByValue(apiAo.getApiRequestContentType()));
        Request request = new Request(apiAo.getApiRequestType(), apiAo.getParameterEntity().getInputParameterSchema(), apiAo.getParameterEntity().getOutputParameterSchema());
        request.initRequest(apiAo.getApiCode(), apiAo.getApiUrl(), apiDebugParam.getHeaderData(), apiDebugParam.getInputParamData());
        Map<String, Object> originalResult = httpClient.sendRequest(request);
        Map<String, Object> result = apiAo.handleApiResponseResult(originalResult);
        return result;
    }

    @Override
    public void parseSwagger(String swaggerData) {
        // 构建解析器
        OpenAPIV3Parser parser = new OpenAPIV3Parser();
        ParseOptions options = new ParseOptions();
        SwaggerParseResult result = parser.readContents(swaggerData, null, options);
        OpenAPI openApi = result.getOpenAPI();

        // 取到paths和components
        Components components = openApi.getComponents();
        Paths paths = openApi.getPaths();

        paths.forEach((path, pathItem) -> {
            handlePathItem(path, pathItem, components);
        });

        System.out.println();
    }

    private void handlePathItem(String path, PathItem pathItem, Components components) {
        handlePost(path, pathItem, components);
    }

    private void handlePost(String path, PathItem pathItem, Components components) {
        Operation postOption = pathItem.getPost();
        if (Objects.isNull(postOption)) {
            return;
        }

        List<InputParameterVO> inputParamList = resolveInputParamList(components, postOption);
        List<OutputParameterVO> outputParamList = resolveOutputParamList(components, postOption);

        ApiAO apiAO = new ApiAO();
        // todo 从外部传来
        apiAO.setSuiteId(-1L);
        // todo 拼接域名
        String requestPath = "https://juggle.com" + path;
        apiAO.setApiUrl(requestPath);
        apiAO.setApiRequestType(RequestTypeEnum.POST);
        apiAO.setApiRequestContentType(RequestContentTypeEnum.APPLICATION_JSON.getValue());
        apiAO.setApiName(postOption.getSummary());
        apiAO.setApiDesc(postOption.getDescription());
        apiAO.initApiCode();
        apiAO.initParameterList(inputParamList, outputParamList);

    }

    private List<OutputParameterVO> resolveOutputParamList(Components components, Operation postOption) {
        ApiResponses responses = postOption.getResponses();
        ApiResponse response = responses.get("200");
        Content content = response.getContent();

        List<OutputParameterVO> parameterList = new ArrayList<>();
        content.forEach((k, v) -> {
            List<OutputParameterVO> nonObjectOutputParamList = resolveNonObjectOutput(v.getSchema());
            List<OutputParameterVO> objectOutputParamList = resolveObjectOutput(components, v.getSchema());
            parameterList.addAll(nonObjectOutputParamList);
            parameterList.addAll(objectOutputParamList);
        });
        return parameterList;
    }

    private List<OutputParameterVO> resolveNonObjectOutput(Schema schema) {
        ArrayList<OutputParameterVO> outputParameterList = new ArrayList<>();
        if (StringUtils.isBlank(schema.getType())) {
            return outputParameterList;
        }

        OutputParameterVO outputParameterVO = new OutputParameterVO();
        outputParameterVO.setParamKey(StringUtils.EMPTY);
        outputParameterVO.setParamName(StringUtils.EMPTY);
        outputParameterVO.setDataType(createDataType(schema));
        outputParameterVO.setParamDesc(StringUtils.EMPTY);
        outputParameterList.add(outputParameterVO);
        return outputParameterList;
    }

    private List<OutputParameterVO> resolveObjectOutput(Components components, Schema v) {
        if (StringUtils.isBlank(v.get$ref())) {
            return Collections.emptyList();
        }

        String refObjectName = getRefObjectName(v);
        Map<String, Schema> schemas = components.getSchemas();
        Schema schema = schemas.get(refObjectName);

        Map<String, Schema> properties = schema.getProperties();
        List<OutputParameterVO> tmpParamList = properties.entrySet().stream()
                .map(x -> {
                    String name = x.getKey();
                    Schema s = x.getValue();
                    return createPostOutputParamVO(name, s, schemas);
                })
                .collect(Collectors.toList());
        return tmpParamList;
    }

    private OutputParameterVO createPostOutputParamVO(String name, Schema s, Map<String, Schema> schemas) {
        OutputParameterVO outputParameterVO = new OutputParameterVO();
        outputParameterVO.setParamKey(name);
        String paramName = getParamName(s);
        String desc = s.getDescription();
        if (StringUtils.isBlank(paramName)) {
            String $ref = s.get$ref();
            if (StringUtils.isNotBlank($ref)) {
                String ref = getRefObjectName(s.get$ref());
                Schema objSchema = schemas.get(ref);
                paramName = getParamName(objSchema);
                desc = objSchema.getDescription();
            }
        }

        outputParameterVO.setParamName(paramName);
        outputParameterVO.setParamDesc(desc);

        DataType dataType = createDataType(name, s, schemas);
        outputParameterVO.setDataType(dataType);
        return outputParameterVO;
    }

    /**
     * 创建数据类型对象，只处理非对象类型参数
     *
     * @param s 当前对象schema
     * @return 数据类型对象
     */
    private DataType createDataType(Schema s) {
        DataTypeEnum typeEnum = DataTypeUtil.from(s);
        // 基础类型处理
        DataType dataType = new DataType(typeEnum);
        return dataType;
    }

    /**
     * 处理对象类型的，兼容处理非对象类型的
     *
     * @param name    参数key
     * @param s       当前对象的schema
     * @param schemas 所有对象的schema集合，用来查找子对象的schema
     * @return 数据类型
     */
    private DataType createDataType(String name, Schema s, Map<String, Schema> schemas) {
        DataTypeEnum typeEnum = DataTypeUtil.from(s);
        // 基础类型处理
        DataType dataType = new DataType(typeEnum);

        // todo 要提前创建对象
        // 如果是对象则做额外的递归处理
        if (DataTypeUtil.isObject(typeEnum)) {
            List<Property> propertyList;
            String objectKey = toUpperCamelCase(name);
            dataType.setObjectKey(objectKey);

            // 判断类型
            String ref = getRefObjectName(s.get$ref());
            Schema paramSchema = schemas.get(ref);
            Map<String, Schema> properties = paramSchema.getProperties();
            propertyList = properties.entrySet().stream()
                    .map(x -> {
                        String k = x.getKey();
                        Schema v = x.getValue();
                        Property property = new Property();
                        property.setPropKey(k);
                        property.setPropName(getParamName(v));
                        property.setDataType(createDataType(k, v, schemas));
                        return property;
                    }).collect(Collectors.toList());

            dataType.setObjectStructure(propertyList);
        }

        // todo 列表待实现
        if (DataTypeUtil.isList(typeEnum)) {
//            dataType.setItemType();
        }
        return dataType;
    }

    public static String toUpperCamelCase(String camelCase) {
        if (camelCase == null || camelCase.isEmpty()) {
            return camelCase;
        }

        if (camelCase.length() == 1) {
            return camelCase.toUpperCase();
        }

        return Character.toUpperCase(camelCase.charAt(0)) + camelCase.substring(1);
    }


    private List<InputParameterVO> resolveInputParamList(Components components, Operation operation) {
        // 解析查询参数
        List<InputParameterVO> queryParameterList = resolveQueryParameter(operation);
        // 解析路径参数
        List<InputParameterVO> pathParameterList = resolvePathParameter(operation);
        // 解析请求体参数
        List<InputParameterVO> bodyParameterList = resolveBodyParameter(components, operation);

        // 整合参数列表
        ArrayList<InputParameterVO> parameterList = new ArrayList<>(queryParameterList.size() + pathParameterList.size() + bodyParameterList.size());
        parameterList.addAll(queryParameterList);
        parameterList.addAll(pathParameterList);
        parameterList.addAll(bodyParameterList);
        return parameterList;
    }

    /**
     * 处理query类型的入参
     *
     * @param operation 请求元数据
     * @return query参数列表
     */
    private List<InputParameterVO> resolveQueryParameter(Operation operation) {
        List<Parameter> parameters = operation.getParameters();
        if (CollectionUtils.isEmpty(parameters)) {
            return Collections.emptyList();
        }
        return parameters.stream()
                .filter(x -> ParameterPositionEnum.QUERY.getCode().equalsIgnoreCase(x.getIn()))
                .map(this::buildQueryParameter)
                .collect(Collectors.toList());
    }


    /**
     * 处理path类型的入参
     *
     * @param operation 请求元数据
     * @return query参数列表
     */
    private List<InputParameterVO> resolvePathParameter(Operation operation) {
        List<Parameter> parameters = operation.getParameters();
        if (CollectionUtils.isEmpty(parameters)) {
            return Collections.emptyList();
        }
        return parameters.stream()
                .filter(x -> ParameterPositionEnum.PATH.getCode().equalsIgnoreCase(x.getIn()))
                .map(this::buildPathParameter)
                .collect(Collectors.toList());
    }

    /**
     * 构建 查询参数
     *
     * @param parameter swagger参数
     * @return 编排参数
     */
    private InputParameterVO buildQueryParameter(Parameter parameter) {
        InputParameterVO inputParameterVO = new InputParameterVO();
        inputParameterVO.setParamKey(parameter.getName());
        inputParameterVO.setParamName(parameter.getDescription());
        inputParameterVO.setParamDesc(parameter.getDescription());
        inputParameterVO.setParamPosition(ParameterPositionEnum.QUERY.getCode());
        inputParameterVO.setRequired(parameter.getRequired());
        DataType dataType = createDataType(parameter.getSchema());
        inputParameterVO.setDataType(dataType);
        return inputParameterVO;
    }

    private InputParameterVO buildPathParameter(Parameter parameter) {
        InputParameterVO inputParameterVO = new InputParameterVO();
        inputParameterVO.setParamKey(parameter.getName());
        inputParameterVO.setParamName(parameter.getDescription());
        inputParameterVO.setParamDesc(parameter.getDescription());
        inputParameterVO.setParamPosition(ParameterPositionEnum.PATH.getCode());
        inputParameterVO.setRequired(parameter.getRequired());
        DataType dataType = createDataType(parameter.getSchema());
        inputParameterVO.setDataType(dataType);
        return inputParameterVO;
    }

    /**
     * 处理放在body请求中的参数
     *
     * @param components 所有的参数对象map
     * @param operation  请求信息
     * @return body入参列表
     */
    private List<InputParameterVO> resolveBodyParameter(Components components, Operation operation) {
        // 处理body参数
        Content content = Optional.ofNullable(operation.getRequestBody()).map(RequestBody::getContent).orElse(null);
        if (Objects.isNull(content)) {
            return Collections.emptyList();
        }

        List<InputParameterVO> parameterList = new ArrayList<>();
        content.forEach((k, v) -> {
            String refObjectName = getRefObjectName(v.getSchema());
            Map<String, Schema> schemas = components.getSchemas();
            Schema schema = schemas.get(refObjectName);

            List<String> requiredList = Optional.ofNullable(schema.getRequired()).orElse(Collections.emptyList());
            Map<String, Schema> properties = schema.getProperties();
            List<InputParameterVO> tmpParamList = properties.entrySet().stream()
                    .map(x -> {
                        String name = x.getKey();
                        Schema s = x.getValue();
                        return createPostInputParamVO(name, s, requiredList, schemas);
                    })
                    .collect(Collectors.toList());
            parameterList.addAll(tmpParamList);
        });
        return parameterList;
    }

    private InputParameterVO createPostInputParamVO(String name, Schema s, List<String> requiredList, Map<String, Schema> schemas) {
        InputParameterVO inputParameterVO = new InputParameterVO();
        inputParameterVO.setParamKey(name);
        String paramName = getParamName(s);
        String desc = s.getDescription();
        if (StringUtils.isBlank(paramName)) {
            String $ref = s.get$ref();
            if (StringUtils.isNotBlank($ref)) {
                String ref = getRefObjectName(s.get$ref());
                Schema objSchema = schemas.get(ref);
                paramName = getParamName(objSchema);
                desc = objSchema.getDescription();
            }
        }
        inputParameterVO.setParamName(paramName);
        inputParameterVO.setParamDesc(desc);

        inputParameterVO.setParamPosition(ParameterPositionEnum.BODY.getCode());
        inputParameterVO.setRequired(requiredList.contains(name));

        DataType dataType = createDataType(name, s, schemas);
        inputParameterVO.setDataType(dataType);
        return inputParameterVO;
    }


    private String getParamName(Schema s) {
        if (StringUtils.isNotBlank(s.getName())) {
            return s.getName();
        }

        if (StringUtils.isNotBlank(s.getTitle())) {
            return s.getTitle();
        }

        if (StringUtils.isNotBlank(s.getDescription())) {
            return s.getDescription();
        }
        return StringUtils.EMPTY;
    }

    private static String getRefObjectName(Schema v) {
        String ref = v.get$ref();
        String[] split = ref.split("/");
        String refObject = split[split.length - 1];
        return refObject;
    }

    private static String getRefObjectName(String ref) {
        String[] split = ref.split("/");
        String refObject = split[split.length - 1];
        return refObject;
    }
}
