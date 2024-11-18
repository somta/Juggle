package net.somta.juggle.console.application.service;

import net.somta.juggle.console.interfaces.dto.OrderDTO;
import net.somta.juggle.console.interfaces.param.OrderParam;

public interface IOrderService {
    OrderDTO createOrder(OrderParam orderParam);

    String getOrderPayStatus(String orderNo);
}
