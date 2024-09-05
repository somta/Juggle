package net.somta.juggle.console.domain.order.repository;

import net.somta.juggle.console.domain.order.vo.CreateOrderVO;

public interface IOrderRepository {

    CreateOrderVO createOrder(String orderName,Integer orderType,Long goodsId);

    String queryOrderPayStatus(String orderNo);

}
