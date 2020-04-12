package io.github.marcoscouto.service.impl;

import io.github.marcoscouto.domain.repository.OrderRepository;
import io.github.marcoscouto.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }



}
