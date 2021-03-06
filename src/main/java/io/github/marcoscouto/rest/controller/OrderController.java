package io.github.marcoscouto.rest.controller;

import io.github.marcoscouto.domain.entity.Order;
import io.github.marcoscouto.domain.entity.OrderItem;
import io.github.marcoscouto.domain.entity.enums.OrderStatus;
import io.github.marcoscouto.exception.BusinessRuleException;
import io.github.marcoscouto.rest.dto.OrderDTO;
import io.github.marcoscouto.rest.dto.OrderInformationDTO;
import io.github.marcoscouto.rest.dto.OrderItemInformationDTO;
import io.github.marcoscouto.rest.dto.UpdateOrderStatusDTO;
import io.github.marcoscouto.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid OrderDTO orderDTO) {
        Order order = orderService.save(orderDTO);
        return order.getId();
    }

    @GetMapping("/{id}")
    public OrderInformationDTO getById(@PathVariable Integer id) {
        return orderService
                .getFullOrder(id)
                .map(x -> getOrderInformationDTO(x))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void updateStatus(@PathVariable Integer id,
                              @RequestBody UpdateOrderStatusDTO updateOrderStatusDTO) {

        orderService.updateStatus(id, OrderStatus.valueOf(updateOrderStatusDTO.getNewStatus()));
    }

    private OrderInformationDTO getOrderInformationDTO(Order order) {
        return OrderInformationDTO
                .builder()
                .code(order.getId())
                .orderData(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(order.getClient().getCpf())
                .nameClient(order.getClient().getName())
                .total(order.getTotal())
                .status(order.getStatus().name())
                .items(getOrderItemInformationDTO(order.getOrderItems()))
                .build();
    }


    private List<OrderItemInformationDTO> getOrderItemInformationDTO(List<OrderItem> items) {
        if (CollectionUtils.isEmpty(items)) return Collections.emptyList();
        return items
                .stream()
                .map(x -> OrderItemInformationDTO
                        .builder()
                        .productDescription(x.getProduct().getDescription())
                        .unitPrice(x.getProduct().getPrice())
                        .quantity(x.getQuantity())
                        .build()
                ).collect(Collectors.toList());
    }
}
