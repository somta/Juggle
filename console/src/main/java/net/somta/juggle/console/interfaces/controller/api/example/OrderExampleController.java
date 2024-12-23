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
package net.somta.juggle.console.interfaces.controller.api.example;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单的示例接口，为系统内置流程提供接口示例，这里的接口，请求内容类型为：application/x-www-form-urlencoded
 * @author Gavin
 */
@Tag(name = "示例-订单的示例接口")
@RestController
@RequestMapping("/example/order")
public class OrderExampleController {

    @Operation(summary = "下单")
    @PostMapping("/placeOrder")
    public OrderDTO placeOrder(@ParameterObject OrderParam orderParam){
        OrderDTO order = new OrderDTO();
        order.setOrderNo("NO123");
        order.setOrderName(orderParam.getOrderName());
        order.setUserId(orderParam.getUserId());
        return order;
    }

    @Operation(summary = "查询订单信息")
    @GetMapping("/getOrderByNo")
    public OrderDTO getOrderByNo(String orderNo){
        OrderDTO order = new OrderDTO();
        order.setOrderNo(orderNo);
        order.setOrderName("测试订单");
        order.setUserId(1);
        return order;
    }

    @Operation(summary = "根据用户ID查询用户订单列表")
    @GetMapping("/getOrderListByUserId/{userId}")
    public UserOrderDTO getOrderListByUserId(@PathVariable Integer userId){
        UserOrderDTO userOrderDto = new UserOrderDTO();
        List<OrderDTO> orderDtoList = new ArrayList<>();
        OrderDTO order = new OrderDTO();
        order.setOrderNo("NO123");
        order.setOrderName("测试订单");
        order.setUserId(userId);
        orderDtoList.add(order);

        OrderDTO order2 = new OrderDTO();
        order2.setOrderNo("NO456");
        order2.setOrderName("测试订单");
        order2.setUserId(userId);
        orderDtoList.add(order2);

        userOrderDto.setUserId(userId);
        userOrderDto.setOrderList(orderDtoList);
        return userOrderDto;
    }

    @Operation(summary = "查询用户订单列表")
    @GetMapping("/getUserOrderList")
    public UserOrderDTO getUserOrderList(@ParameterObject OrderParam orderParam){
        UserOrderDTO userOrderDto = new UserOrderDTO();
        List<OrderDTO> orderDtoList = new ArrayList<>();
        OrderDTO order = new OrderDTO();
        order.setOrderNo("NO123");
        order.setOrderName("测试订单");
        order.setUserId(orderParam.getUserId());
        orderDtoList.add(order);

        OrderDTO order2 = new OrderDTO();
        order2.setOrderNo("NO456");
        order2.setOrderName("测试订单");
        order2.setUserId(orderParam.getUserId());
        orderDtoList.add(order2);

        userOrderDto.setUserId(orderParam.getUserId());
        userOrderDto.setOrderList(orderDtoList);
        return userOrderDto;
    }

    public static class OrderParam{
        private String orderName;
        private Integer userId;

        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }
    }

    public static class OrderDTO{
        private String orderNo;
        private String orderName;
        private Integer userId;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }
    }

    public static class UserOrderDTO{
        private Integer userId;
        private List<OrderDTO> orderList;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public List<OrderDTO> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderDTO> orderList) {
            this.orderList = orderList;
        }
    }

}
