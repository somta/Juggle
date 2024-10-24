package net.somta.juggle.console.infrastructure.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.domain.order.repository.IOrderRepository;
import net.somta.juggle.console.domain.order.vo.CreateOrderVO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_OPEN_DOMAIN;

@Repository
public class OrderRepositoryImpl implements IOrderRepository {

    private final RestTemplate restTemplate;

    public OrderRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CreateOrderVO createOrder(String orderName, Integer orderType, Long goodsId) {
        CreateOrderVO createOrder = null;
        Map<String,Object> param = new HashMap<>();
        param.put("orderName",orderName);
        param.put("orderType",orderType);
        param.put("goodsId",goodsId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = null;
        try {
            entity = new HttpEntity<>(JsonSerializeHelper.serialize(param),headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ResponseEntity<ResponseDataResult<CreateOrderVO>> response = restTemplate.exchange(
                JUGGLE_OPEN_DOMAIN+"/open/v1/order/create",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ResponseDataResult<CreateOrderVO>>() {});
        if(response.getStatusCode() == HttpStatus.OK){
            createOrder = response.getBody().getResult();
        }
        return createOrder;
    }

    @Override
    public String queryOrderPayStatus(String orderNo) {
        String bill = null;
        Map<String,Object> param = new HashMap<>();
        param.put("orderNo",orderNo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = null;
        try {
            entity = new HttpEntity<>(JsonSerializeHelper.serialize(param),headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ResponseEntity<ResponseDataResult<String>> response = restTemplate.exchange(
                JUGGLE_OPEN_DOMAIN+"/open/v1/order/pay/status",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ResponseDataResult<String>>() {});
        if(response.getStatusCode() == HttpStatus.OK){
            bill = response.getBody().getResult();
        }
        return bill;
    }
}
