package com.software.repository;

import com.software.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {
    Page<Brand> findAll(Pageable pageable);
    @Query("select b from Brand b where b.isActive = true")
    Page<Brand> findAllByActive(Pageable pageable);
    Brand findBrandByBrandName(String name);
}
