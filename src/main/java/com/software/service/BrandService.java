package com.software.service;

import com.software.dto.BrandDTO;
import org.springframework.data.domain.Page;

public interface BrandService {
    Page<BrandDTO> getAllBrand(int page, int size);
    BrandDTO getBrandById(Long id);
    BrandDTO getBrandByName(String name);
    BrandDTO saveBrand(BrandDTO brand);
    BrandDTO updateBrand(Long id, BrandDTO newBrand);
    void deleteBrand(Long id);
}
