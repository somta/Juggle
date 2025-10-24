package net.somta.juggle.console.application.service.suite.impl;

import net.somta.juggle.console.domain.suite.api.repository.IApiRepository;
import net.somta.juggle.console.domain.suite.suiteinfo.repository.ISuiteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * desc:
 *
 * @author chen shuai
 * @date 2025/10/24 17:49
 */
@ExtendWith(SpringExtension.class)
class ApiServiceImplTest {
    @InjectMocks
    private ApiServiceImpl apiService;

    @Mock
    private IApiRepository apiRepository;
    @Mock
    private ISuiteRepository suiteRepository;

    @Test
    void testParseSwagger() {
        apiService.parseSwagger(mockSwaggerData());
    }


    private static String mockSwaggerData() {
        return "{\"paths\":{\"/cus/content/app/visitor/runtime/serverAuth\":{\"post\":{\"tags\":[\"参与者接口(C端)\"],\"summary\":\"服务端授权\",\"description\":\"服务端授权\",\"operationId\":\"serverAuth\",\"requestBody\":{\"content\":{\"application/json\":{\"schema\":{\"$ref\":\"#/components/schemas/ServerAuthParam\"}}},\"required\":true},\"responses\":{\"200\":{\"description\":\"OK\",\"content\":{\"*/*\":{\"schema\":{\"$ref\":\"#/components/schemas/AuthDTO\"}}}}}}}},\"components\":{\"schemas\":{\"ServerAuthParam\":{\"required\":[\"appId\",\"contentId\",\"platformId\",\"tenantId\"],\"type\":\"object\",\"properties\":{\"appId\":{\"type\":\"integer\",\"description\":\"应用id\",\"format\":\"int64\"},\"tenantId\":{\"type\":\"integer\",\"description\":\"租户id\",\"format\":\"int64\"},\"contentId\":{\"type\":\"integer\",\"description\":\"作品id\",\"format\":\"int64\"},\"platform\":{\"type\":\"string\",\"description\":\"来源平台, 不传则默认为integration\"},\"platformId\":{\"type\":\"string\",\"description\":\"来源平台id\"},\"sourceEnv\":{\"type\":\"string\",\"description\":\"所处环境（非必填）\"},\"accessToken\":{\"type\":\"string\",\"description\":\"用于置换用户信息的token\"}},\"description\":\"服务端授权入参\"},\"AuthDTO\":{\"type\":\"object\",\"properties\":{\"token\":{\"type\":\"string\",\"description\":\"jwt串\"},\"userInfo\":{\"$ref\":\"#/components/schemas/UserInfo\"},\"enc\":{\"$ref\":\"#/components/schemas/EncryptionInfo\"},\"authUrl\":{\"type\":\"string\",\"description\":\"重定向地址, 如果该字段不为空, 说明能直接获取到用户凭证, 如果为空, 则说明需要重定向到该地址进行授权\"}}},\"UserInfo\":{\"type\":\"object\",\"properties\":{\"nickname\":{\"type\":\"string\",\"description\":\"用户昵称\"},\"headImgUrl\":{\"type\":\"string\",\"description\":\"用户头像地址\"}},\"description\":\"用户信息\"},\"EncryptionInfo\":{\"type\":\"object\",\"properties\":{\"req\":{\"type\":\"string\",\"description\":\"请求加密公钥\"},\"res\":{\"type\":\"string\",\"description\":\"响应解密私钥\"}},\"description\":\"加解密秘钥信息\"}}},\"openapi\":\"3.0.1\"}";
    }
}