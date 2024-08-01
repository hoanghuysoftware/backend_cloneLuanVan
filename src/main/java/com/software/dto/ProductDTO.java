package com.software.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private String ram;
    private String rom;
    private String cpu;
    private String card;
    private String screen;
    private String description;
    private boolean isActive = true;
    private Long brand;
    private Long sale;
    private List<Long> typeProduct;
}
