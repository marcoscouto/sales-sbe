package io.github.marcoscouto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100)
    @NotEmpty(message = "Name is required")
    private String name;

    private String cpf;

    //    @JsonIgnoreProperties("client")
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private Set<Order> orders;

    public Set<Order> getOrders() {
        return orders == null ? new HashSet<>() : orders;
    }
}
