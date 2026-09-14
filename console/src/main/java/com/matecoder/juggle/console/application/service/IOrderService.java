package com.matecoder.juggle.console.application.service;

import com.matecoder.juggle.console.interfaces.dto.OrderDTO;
import com.matecoder.juggle.console.interfaces.param.OrderParam;

public interface IOrderService {
    OrderDTO createOrder(OrderParam orderParam);

    String getOrderPayStatus(String orderNo);
}
