package io.github.marcoscouto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnoreProperties("order")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @JsonIgnoreProperties("product")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    public OrderItem() {
    }

    public OrderItem(Integer id, Order order, Product product, Integer quantity) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) &&
                Objects.equals(order, orderItem.order) &&
                Objects.equals(quantity, orderItem.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, quantity);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderItem{");
        sb.append("id=").append(id);
        sb.append(", order=").append(order);
        sb.append(", product=").append(product);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
