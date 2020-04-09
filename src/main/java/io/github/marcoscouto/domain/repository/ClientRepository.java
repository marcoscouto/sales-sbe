package io.github.marcoscouto.domain.repository;

import io.github.marcoscouto.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Client save(Client client) {
        entityManager.persist(client);
        return client;
    }

    @Transactional
    public Client update(Client client) {
        entityManager.merge(client);
        return client;
    }

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return entityManager.createQuery("from Client", Client.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Client> findById(Integer id) {
        String jpql = "select c from Client c where c.id = :id";
        final TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Client> findByName(String name) {
        String jpql = "select c from Client c where c.nome like :nome";
        final TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Transactional
    public void delete(Integer id) {
        Client client = entityManager.find(Client.class, id);
        if (entityManager.contains(client)) entityManager.remove(client);
    }

}
