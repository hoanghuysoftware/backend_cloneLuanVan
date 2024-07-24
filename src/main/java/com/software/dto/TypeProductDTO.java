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
public class TypeProductDTO {
    @NotBlank(message = "Name type product is required !")
    @Size(min = 3, max = 20, message = "The length of type name product between 3 and 20 characters !")
    private String typeName;
}
