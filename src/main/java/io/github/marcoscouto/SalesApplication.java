package io.github.marcoscouto;

import io.github.marcoscouto.domain.entity.Client;
import io.github.marcoscouto.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }

    @Bean
    public CommandLineRunner run (@Autowired ClientRepository clientRepository){
        return args -> {
            clientRepository.save(new Client(null, "Marcos"));
            clientRepository.findAll().forEach(System.out::println);
        };
    }

}
