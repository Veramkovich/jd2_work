package it.academy.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PromoDto {

    @NotEmpty
    private String productName;

    @NotEmpty
    private String description;

    @NotEmpty
    private String promoDescription;

}
