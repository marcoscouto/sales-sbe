package io.github.marcoscouto.service;

import io.github.marcoscouto.domain.entity.Order;
import io.github.marcoscouto.rest.dto.OrderDTO;

public interface OrderService {

    Order save(OrderDTO orderDTO);

}
