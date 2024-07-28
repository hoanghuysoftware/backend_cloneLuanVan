package com.software.service;

import com.software.dto.SaleDTO;
import com.software.model.Sale;

import java.util.Date;
import java.util.List;

public interface SaleService {
    List<Sale> gteAllSale();
    List<Sale> getSaleByName(String name);
    List<Sale> getSaleByBetweenDate(Date start, Date end);
    Sale getSaleById(Long id);
    Sale createNewSale(SaleDTO saleDTO);
    Sale updateSale(Long id, SaleDTO saleDTO);
    void deleteSale(Long id);
}
