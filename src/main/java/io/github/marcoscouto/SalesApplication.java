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
            Client client1 = new Client(null, "Marcos");
            Client client2 = new Client(null, "João");
            clientRepository.save(client1);
            clientRepository.save(client2);
            client2 = clientRepository.findById(2).get();
            client2.setName("Gerard");
            clientRepository.save(client2);
            clientRepository.deleteById(1);
            clientRepository.findAll().forEach(System.out::println);

            System.out.println(clientRepository.customNativeSQLFindByName("Gera"));
        };
    }

}
