package io.github.marcoscouto.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusDTO {
    private String newStatus;
}
