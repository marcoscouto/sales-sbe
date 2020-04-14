package io.github.marcoscouto.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemInformationDTO {

    private String productDescription;
    private BigDecimal unitPrice;
    private Integer quantity;

}
