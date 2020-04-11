package io.github.marcoscouto.rest.controller;

import io.github.marcoscouto.domain.entity.Client;
import io.github.marcoscouto.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Integer id){
        Client client = clientRepository.findById(id).get();
        HttpHeaders hh = new HttpHeaders();
        hh.set("Authorization", "token");
        ResponseEntity<Client> responseEntity = new ResponseEntity<Client>(client, hh, HttpStatus.OK);
        return responseEntity;
    }

}
