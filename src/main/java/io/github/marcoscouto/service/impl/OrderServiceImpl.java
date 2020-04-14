package io.github.marcoscouto.service.impl;

import io.github.marcoscouto.domain.entity.Client;
import io.github.marcoscouto.domain.entity.Order;
import io.github.marcoscouto.domain.entity.OrderItem;
import io.github.marcoscouto.domain.entity.Product;
import io.github.marcoscouto.domain.entity.enums.OrderStatus;
import io.github.marcoscouto.domain.repository.ClientRepository;
import io.github.marcoscouto.domain.repository.OrderItemRepository;
import io.github.marcoscouto.domain.repository.OrderRepository;
import io.github.marcoscouto.domain.repository.ProductRepository;
import io.github.marcoscouto.exception.BusinessRuleException;
import io.github.marcoscouto.exception.OrderNotFoundException;
import io.github.marcoscouto.rest.dto.OrderDTO;
import io.github.marcoscouto.rest.dto.OrderItemDTO;
import io.github.marcoscouto.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Order save(OrderDTO orderDTO) {
        Client client = clientRepository
                .findById(orderDTO.getClient())
                .orElseThrow(() -> new BusinessRuleException("Client code invalid"));

        Order order = new Order();
        order.setTotal(orderDTO.getTotal());
        order.setOrderDate(LocalDate.now());
        order.setClient(client);
        order.setStatus(OrderStatus.REALIZED);
        List<OrderItem> items = saveItems(order, orderDTO.getItems());
        orderRepository.save(order);
        orderItemRepository.saveAll(items);
        order.setOrderItems(items);
        return order;
    }

    @Override
    public Optional<Order> getFullOrder(Integer id) {
        return orderRepository.findByIdFetchOrderItems(id);
    }

    @Override
    @Transactional
    public void updateStatus(Integer id, OrderStatus orderStatus) {
        orderRepository
                .findById(id)
                .map(x -> {
                    x.setStatus(orderStatus);
                    return orderRepository.save(x);
                }).orElseThrow(() -> new OrderNotFoundException());
    }

    private List<OrderItem> saveItems(Order order, List<OrderItemDTO> items) {
        if (items.isEmpty()) throw new BusinessRuleException("Items list is empty");
        return items
                .stream()
                .map(orderItemDTO -> {
                    Integer productId = orderItemDTO.getProduct();
                    Product product = productRepository
                            .findById(productId)
                            .orElseThrow(() -> new BusinessRuleException("Product not found"));

                    return new OrderItem(null, order, product, orderItemDTO.getQuantity());
                })
                .collect(Collectors.toList());
    }
}
