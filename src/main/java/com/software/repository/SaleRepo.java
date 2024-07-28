package com.software.repository;

import com.software.model.Sale;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepo extends JpaRepository<Sale, Long> {
    boolean existsByNameSale(String name);
    List<Sale> getSaleByNameSaleContaining(String name);
    Sale getSaleByIdSale(Long id);
    @Query("select s from Sale s where s.startDate <= :endDate and s.endDate >= :startDate")
    List<Sale> getAllBySaleByBetweetDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
