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
package net.somta.juggle.console.interfaces.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.application.service.IOrderService;
import net.somta.juggle.console.interfaces.dto.OrderDTO;
import net.somta.juggle.console.interfaces.dto.UserDTO;
import net.somta.juggle.console.interfaces.param.OrderParam;
import org.springframework.web.bind.annotation.*;


import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;


/**
 * @author Gavin
 * @since 1.2.2
 */
@Tag(name = "订单接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/order")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "创建订单")
    @PostMapping("/createOrder")
    public ResponseDataResult<OrderDTO> createOrder(@RequestBody OrderParam orderParam){
        OrderDTO orderDto = orderService.createOrder(orderParam);
        return ResponseDataResult.setResponseResult(orderDto);
    }

    @Operation(summary = "查询订单支付状态")
    @GetMapping("/getOrderPayStatus/{orderNo}")
    public ResponseDataResult<String> getOrderPayStatus(@PathVariable String orderNo){
        String bill = orderService.getOrderPayStatus(orderNo);
        return ResponseDataResult.setResponseResult(bill);
    }



}
