package io.github.marcoscouto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnoreProperties("client")
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonIgnoreProperties("order")
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    private LocalDate orderDate;

    @Column(scale = 2)
    private BigDecimal total;

    public List<OrderItem> getOrderItems() {
        return orderItems == null ? new ArrayList<>() : orderItems;
    }
}
