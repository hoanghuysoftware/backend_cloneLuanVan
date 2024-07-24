package com.software.service.serviceIMPL;

import com.software.dto.TypeProductDTO;
import com.software.mapper.TypeProductConvert;
import com.software.model.TypeProduct;
import com.software.repository.TypeProductRepo;
import com.software.service.TypeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TypeProductServiceIMPL implements TypeProductService {
    private final TypeProductRepo typeProductRepo;
    private final TypeProductConvert typeProductConvert;

    @Override
    public Page<TypeProduct> getAllTypeProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return typeProductRepo.findAll(pageable);
    }

    @Override
    public Page<TypeProductDTO> getAllTypeProductIsActive(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TypeProduct> result = typeProductRepo.findAllByActiveTrue(pageable);
        return result.map(typeProductConvert::convertTypeProductDTO);
    }

    @Override
    public TypeProduct getTypeProductById(Long id) {
        return typeProductRepo.getTypeProductByIdTypeProduct(id);
    }

    @Override
    public TypeProduct saveTypeProduct(TypeProductDTO typeProduct) {
        TypeProduct newTypeProduct = TypeProduct.builder()
                .typeNameProduct(typeProduct.getTypeName())
                .isActive(true)
                .build();
        return typeProductRepo.save(newTypeProduct);
    }

    @Override
    @Transactional
    public TypeProduct updateTypeProduct(Long id, TypeProductDTO newTypeProduct) {
        TypeProduct oldTypeProduct = typeProductRepo.getTypeProductByIdTypeProduct(id);
        if (oldTypeProduct != null) {
            oldTypeProduct.setTypeNameProduct(newTypeProduct.getTypeName());
            return typeProductRepo.save(oldTypeProduct);
        }
        return null;
    }

    @Override
    public void deleteTypeProduct(Long id) {
        TypeProduct oldTypeProduct = typeProductRepo.getTypeProductByIdTypeProduct(id);
        if (oldTypeProduct!=null) {
            oldTypeProduct.setActive(false);
            typeProductRepo.save(oldTypeProduct);
        }
    }
}
