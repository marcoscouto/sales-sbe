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

    @NotNull(message = "{field.client-code.required}")
    private Integer client;

    @NotNull(message = "{field.order-total.required}")
    private BigDecimal total;

    @NotEmptyList(message = "{field.order-items.required}")
    private List<OrderItemDTO> items;


}
