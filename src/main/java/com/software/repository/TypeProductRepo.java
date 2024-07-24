package com.software.repository;

import com.software.model.TypeProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface TypeProductRepo extends JpaRepository<TypeProduct, Long> {
    Page<TypeProduct> findAll(Pageable pageable);
    @Query("select t from TypeProduct t where t.isActive = true")
    Page<TypeProduct> findAllByActiveTrue(Pageable pageable);
    TypeProduct getTypeProductByIdTypeProduct(Long id);
    TypeProduct getTypeProductByTypeNameProduct(String typeName);

}
