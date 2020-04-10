package io.github.marcoscouto.domain.repository;

import io.github.marcoscouto.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
