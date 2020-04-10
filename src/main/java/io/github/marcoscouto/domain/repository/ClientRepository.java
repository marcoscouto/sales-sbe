package io.github.marcoscouto.domain.repository;

import io.github.marcoscouto.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByNameLike(String name);

    List<Client> findByNameOrIdOrderById(String name, Integer id);

    Client findOneByName(String name);

    boolean existsByName(String name);

}
