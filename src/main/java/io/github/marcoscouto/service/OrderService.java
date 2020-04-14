package io.github.marcoscouto.service;

import io.github.marcoscouto.domain.entity.Order;
import io.github.marcoscouto.rest.dto.OrderDTO;

import java.util.Optional;

public interface OrderService {

    Order save(OrderDTO orderDTO);

    Optional<Order> getFullOrder(Integer id);

}
