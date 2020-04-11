package io.github.marcoscouto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private Set<OrderItem> orderItem = new HashSet<>();

    private LocalDate orderDate;

    @Column(scale = 2)
    private BigDecimal total;

    public Order() {
    }

    public Order(Integer id, Client client, LocalDate orderDate, BigDecimal total) {
        this.id = id;
        this.client = client;
        this.orderDate = orderDate;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<OrderItem> getOrderItem() {
        return orderItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(client, order.client) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(total, order.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, orderDate, total);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
        sb.append("id=").append(id);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}
