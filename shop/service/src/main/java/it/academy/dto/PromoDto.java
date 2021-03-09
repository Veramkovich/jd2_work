package it.academy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PromoDto {

    @NotBlank
    @Size(min = 5, max = 50)
    private String productName;

    @NotBlank
    @Size(min = 5, max = 50)
    private String description;

    @NotBlank
    @Size(min = 5, max = 50)
    private String promoDescription;

}
