package io.github.marcoscouto.domain.repository;

import io.github.marcoscouto.domain.entity.Client;
import io.github.marcoscouto.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByClient(Client client);

}
