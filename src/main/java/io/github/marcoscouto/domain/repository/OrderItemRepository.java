package io.github.marcoscouto.domain.repository;

import io.github.marcoscouto.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
