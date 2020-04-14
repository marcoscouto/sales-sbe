package io.github.marcoscouto.config;

import io.github.marcoscouto.domain.entity.Client;
import io.github.marcoscouto.domain.entity.Order;
import io.github.marcoscouto.domain.entity.OrderItem;
import io.github.marcoscouto.domain.entity.Product;
import io.github.marcoscouto.domain.entity.enums.OrderStatus;
import io.github.marcoscouto.domain.repository.ClientRepository;
import io.github.marcoscouto.domain.repository.OrderItemRepository;
import io.github.marcoscouto.domain.repository.OrderRepository;
import io.github.marcoscouto.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {

        Client cli1 = new Client();
        cli1.setName("Marcos");
        cli1.setCpf("91168423082");

        Client cli2 = new Client();
        cli2.setName("João");
        cli2.setCpf("28340438093");

        clientRepository.saveAll(Arrays.asList(cli1, cli2));

        Product p1 = new Product(null, "TV", new BigDecimal(1050.99));
        Product p2 = new Product(null, "Computer", new BigDecimal(2100.30));
        Product p3 = new Product(null, "Ipad", new BigDecimal(367.00));
        Product p4 = new Product(null, "Table", new BigDecimal(105.70));

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

        OrderItem oi1 = new OrderItem(null, null, p1, 1);
        OrderItem oi2 = new OrderItem(null, null, p2, 1);
        OrderItem oi3 = new OrderItem(null, null, p3, 1);
        OrderItem oi4 = new OrderItem(null, null, p4, 1);

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        Order o1 = new Order(null, cli1, Arrays.asList(oi1, oi3),  LocalDate.now(), null, OrderStatus.REALIZED);
        Order o2 = new Order(null, cli2, Arrays.asList(oi2, oi4), LocalDate.now(), null, OrderStatus.REALIZED);

        o1.setTotal(new BigDecimal(
                o1.getOrderItems()
                        .stream()
                        .mapToDouble(x -> x.getQuantity() * x.getProduct().getPrice().doubleValue())
                        .sum()
        ));

        o2.setTotal(new BigDecimal(
                o2.getOrderItems()
                        .stream()
                        .mapToDouble(x -> x.getQuantity() * x.getProduct().getPrice().doubleValue())
                        .sum()
        ));

        orderRepository.saveAll(Arrays.asList(o1, o2));

        oi1.setOrder(o1);
        oi2.setOrder(o2);
        oi3.setOrder(o1);
        oi4.setOrder(o2);

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        cli1.getOrders().add(o1);
        cli2.getOrders().add(o2);

        clientRepository.saveAll(Arrays.asList(cli1, cli2));

//            Client result = clientRepository.findClientFetchOrders(1);

        DecimalFormat df = new DecimalFormat("#,###,###.00");
        List<Order> result = orderRepository.findByClient(cli1);
        result.forEach(x -> {
            System.out.println("Client: " + x.getClient().getName());
            System.out.println("Order: " + x.getId());
            System.out.println("Date: " + x.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Total: R$ " + df.format(x.getTotal().doubleValue()));
        });

    }
}
