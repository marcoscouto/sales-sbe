package io.github.marcoscouto.rest.controller;

import io.github.marcoscouto.domain.entity.Client;
import io.github.marcoscouto.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/{id}")
    public Client findById(@PathVariable Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client save(@RequestBody @Valid Client client) {
        return clientRepository.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return client;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Client client) {
        clientRepository.findById(id)
                .map(x -> {
                    client.setId(x.getId());
                    clientRepository.save(client);
                    return ResponseEntity.ok(client);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @GetMapping
    public List<Client> findByProperties(Client data){
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(data, exampleMatcher);
        return clientRepository.findAll(example);
    }

}
