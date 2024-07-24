package com.software.mapper;

import com.software.dto.BrandDTO;
import com.software.model.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandConvert {
    public Brand covertToBrand(BrandDTO brandDTO){
        return Brand.builder()
                .brandName(brandDTO.getBrandName())
                .build();
    }
    public BrandDTO covertToBrandDTO(Brand brand){
        return BrandDTO.builder()
                .brandName(brand.getBrandName())
                .build();
    }
}
