package net.somta.juggle.console.application.service.impl;

import net.somta.juggle.console.application.assembler.IOrderAssembler;
import net.somta.juggle.console.application.service.IOrderService;
import net.somta.juggle.console.domain.order.repository.IOrderRepository;
import net.somta.juggle.console.domain.order.vo.CreateOrderVO;
import net.somta.juggle.console.interfaces.dto.OrderDTO;
import net.somta.juggle.console.interfaces.param.OrderParam;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;

    public OrderServiceImpl(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO createOrder(OrderParam orderParam) {
        CreateOrderVO order = orderRepository.createOrder(orderParam.getOrderName(),orderParam.getOrderType(),orderParam.getGoodsId());
        OrderDTO orderDto = IOrderAssembler.IMPL.voToDto(order);
        return orderDto;
    }

    @Override
    public String getOrderPayStatus(String orderNo) {
        return orderRepository.queryOrderPayStatus(orderNo);
    }
}
