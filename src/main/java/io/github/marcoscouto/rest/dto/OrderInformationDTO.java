package io.github.marcoscouto.rest.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInformationDTO {

    private Integer code;
    private String nameClient;
    private String cpf;
    private BigDecimal total;
    private String orderData;
    private List<OrderItemInformationDTO> items;

}
