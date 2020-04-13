package io.github.marcoscouto.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {

    private Integer client;
    private BigDecimal total;
    private List<OrderItemDTO> items;


}
