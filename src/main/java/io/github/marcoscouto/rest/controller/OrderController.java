package io.github.marcoscouto.rest.controller;

import io.github.marcoscouto.domain.entity.Order;
import io.github.marcoscouto.rest.dto.OrderDTO;
import io.github.marcoscouto.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody OrderDTO orderDTO){
        Order order = orderService.save(orderDTO);
        return order.getId();
    }
}
