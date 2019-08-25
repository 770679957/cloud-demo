package cn.yyw.microservice.order.controller;

import cn.yyw.microservice.order.pojo.Order;
import cn.yyw.microservice.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author win10
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 127.0.0.1:8082/order/59193738268961441
    @GetMapping(value = "order/{orderId}")
    public Order queryOrderById(@PathVariable("orderId") String orderId) {
        return this.orderService.queryOrderById(orderId);
    }

}
