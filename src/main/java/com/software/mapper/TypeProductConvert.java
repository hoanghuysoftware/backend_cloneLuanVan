package com.software.mapper;

import com.software.dto.TypeProductDTO;
import com.software.model.TypeProduct;
import org.springframework.stereotype.Component;

@Component
public class TypeProductConvert {
    public TypeProduct convertTypeProduct(TypeProductDTO typeProductDTO){
        return TypeProduct.builder()
                .typeNameProduct(typeProductDTO.getTypeName())
                .build();
    }

    public TypeProductDTO convertTypeProductDTO(TypeProduct typeProduct){
        return TypeProductDTO.builder()
                .typeName(typeProduct.getTypeNameProduct())
                .build();
    }
}
