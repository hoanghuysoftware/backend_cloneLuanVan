package com.software.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProviderDTO {
    private String nameProvider;
    private String phoneNumberProvider;
    private String emailProvider;
}
