package net.somta.juggle.console.infrastructure.repository;

import net.somta.common.utils.httpclient.HttpClientUtil;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.domain.order.repository.IOrderRepository;
import net.somta.juggle.console.domain.order.vo.CreateOrderVO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_OPEN_DOMAIN;

@Repository
public class OrderRepositoryImpl implements IOrderRepository {
    @Override
    public CreateOrderVO createOrder(String orderName, Integer orderType, Long goodsId) {
        CreateOrderVO createOrder = null;
        Map<String,Object> param = new HashMap<>();
        param.put("orderName",orderName);
        param.put("orderType",orderType);
        param.put("goodsId",goodsId);
        ResponseDataResult result = HttpClientUtil.doPost(JUGGLE_OPEN_DOMAIN+"/open/v1/order/create", JsonSerializeHelper.serialize(param));
        if(result.isSuccess()){
            ResponseDataResult resultData = JsonSerializeHelper.deserialize(String.valueOf(result.getResult()),ResponseDataResult.class);
            createOrder = JsonSerializeHelper.deserialize(JsonSerializeHelper.serialize(resultData.getResult()), CreateOrderVO.class);
        }
        return createOrder;
    }

    @Override
    public String queryOrderPayStatus(String orderNo) {
        Map<String,Object> param = new HashMap<>();
        param.put("orderNo",orderNo);
        ResponseDataResult result = HttpClientUtil.doPost(JUGGLE_OPEN_DOMAIN+"/open/v1/order/pay/status", JsonSerializeHelper.serialize(param));
        if(result.isSuccess()){
            ResponseDataResult resultData = JsonSerializeHelper.deserialize(String.valueOf(result.getResult()),ResponseDataResult.class);
            if(resultData.getResult() != null){
                return resultData.getResult().toString();
            }
        }
        return null;
    }
}
