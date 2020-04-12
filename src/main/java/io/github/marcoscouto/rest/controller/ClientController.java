package io.github.marcoscouto.rest.controller;

import io.github.marcoscouto.domain.entity.Client;
import io.github.marcoscouto.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if (!client.isPresent()) ResponseEntity.notFound().build();
        HttpHeaders hh = new HttpHeaders();
        hh.set("Authorization", "token");
        ResponseEntity<Client> responseEntity = new ResponseEntity<Client>(client.get(), hh, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client) {
        Client response = clientRepository.save(client);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Client> response = clientRepository.findById(id);

        if (!response.isPresent()) return ResponseEntity.notFound().build();

        clientRepository.delete(response.get());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Integer id, @RequestBody Client client) {
        return clientRepository.findById(id)
                .map(x -> {
                    client.setId(x.getId());
                    clientRepository.save(client);
                    return ResponseEntity.ok(client);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
