package io.github.marcoscouto.domain.repository;

import io.github.marcoscouto.domain.entity.Client;
import io.github.marcoscouto.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByClient(Client client);

    @Query("select o from Order o left join fetch o.orderItems where o.id = :id ")
    Optional<Order> findByIdFetchOrderItems(@Param(value = "id") Integer id);

}
