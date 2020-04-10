package io.github.marcoscouto.domain.repository;

import io.github.marcoscouto.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
