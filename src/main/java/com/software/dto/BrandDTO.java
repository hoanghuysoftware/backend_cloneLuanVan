package com.software.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {
    @NotBlank(message = "Name brand is required !")
    @Size(min = 3, max = 20, message = "Brand name length from 3 to 20 characters !")
    private String brandName;
}
