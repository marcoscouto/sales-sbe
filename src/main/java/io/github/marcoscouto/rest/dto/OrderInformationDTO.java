package io.github.marcoscouto.rest.dto;

import io.github.marcoscouto.domain.entity.enums.OrderStatus;
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
    private String status;
    private List<OrderItemInformationDTO> items;

}
