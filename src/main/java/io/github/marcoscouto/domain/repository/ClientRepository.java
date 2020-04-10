package io.github.marcoscouto.domain.repository;

import io.github.marcoscouto.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByNameLike(String name);

    @Query(value = "select c from Client c where c.name like %:name%")
    List<Client> customFindByName(@Param("name") String name);

    @Query(value = "select * from tb_client c where c.name like %:name%", nativeQuery = true)
    List<Client> customNativeSQLFindByName(@Param("name") String name);

    @Query("delete from Client c where c.name = :name")
    @Modifying // Transaction
    void deleteByName(@Param(value = "name") String name);

    List<Client> findByNameOrIdOrderById(String name, Integer id);

    Client findOneByName(String name);

    boolean existsByName(String name);

    @Query("select c from Client c left join fetch c.orders where c.id = :id")
    Client findClientFetchOrders(@Param(value = "id") Integer id);

}
