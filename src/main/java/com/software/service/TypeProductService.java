package com.software.service;

import com.software.dto.TypeProductDTO;
import com.software.model.TypeProduct;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface TypeProductService {
    Page<TypeProduct> getAllTypeProduct(int page, int size);
    Page<TypeProductDTO> getAllTypeProductIsActive(int page, int size);
    TypeProduct getTypeProductById(Long id);
    TypeProduct saveTypeProduct(TypeProductDTO typeProduct);
    TypeProduct updateTypeProduct(Long id, TypeProductDTO newTypeProduct);
    void deleteTypeProduct(Long id);
}
