package io.github.marcoscouto.domain.repository;

import io.github.marcoscouto.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByUsername(String username);
}
