package io.github.marcoscouto.rest.dto;

import io.github.marcoscouto.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @NotNull(message = "Client code is required")
    private Integer client;

    @NotNull(message = "Total is required")
    private BigDecimal total;

    @NotEmptyList(message = "Order not be realized without items")
    private List<OrderItemDTO> items;


}
