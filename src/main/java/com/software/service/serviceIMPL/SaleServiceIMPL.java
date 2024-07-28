package com.software.service.serviceIMPL;

import com.software.dto.SaleDTO;
import com.software.exception.NameExistsException;
import com.software.exception.NotFoundEntityException;
import com.software.model.Sale;
import com.software.repository.SaleRepo;
import com.software.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceIMPL implements SaleService {
    private final SaleRepo saleRepo;
    @Override
    public List<Sale> gteAllSale() {
        return saleRepo.findAll();
    }

    @Override
    public List<Sale> getSaleByName(String name) {
        return saleRepo.getSaleByNameSaleContaining(name);
    }

    @Override
    public List<Sale> getSaleByBetweenDate(Date start, Date end) {
        return saleRepo.getAllBySaleByBetweetDate(start, end);
    }

    @Override
    public Sale getSaleById(Long id) {
        return saleRepo.getSaleByIdSale(id);
    }

    @Override
    public Sale createNewSale(SaleDTO saleDTO) {
        boolean existsName = saleRepo.existsByNameSale(saleDTO.getNameSale());
        if (existsName) {
            throw new NameExistsException("Sale name already exists");
        }
        Sale sale = Sale.builder()
                .nameSale(saleDTO.getNameSale())
                .amount(saleDTO.getAmount())
                .startDate(saleDTO.getStartDate())
                .endDate(saleDTO.getEndDate())
                .build();
        return saleRepo.save(sale);
    }

    @Override
    public Sale updateSale(Long id, SaleDTO saleDTO) {
//        Check sale not existed
        Sale oldSale = saleRepo.getSaleByIdSale(id);
        if (oldSale == null) {
            throw new NotFoundEntityException("Not found Sale by id " + id);
        }
//        Check name exists
        boolean existsName = saleRepo.existsByNameSale(saleDTO.getNameSale());
        if (existsName) {
            throw new NameExistsException("Sale name already exists");
        }

//        Handle update old sale from SaleDTO entity
        oldSale.setNameSale(saleDTO.getNameSale());
        oldSale.setAmount(saleDTO.getAmount());
        oldSale.setStartDate(saleDTO.getStartDate());
        oldSale.setEndDate(saleDTO.getEndDate());
        return saleRepo.save(oldSale);
    }

    @Override
    public void deleteSale(Long id) {
        Sale oldSale = saleRepo.getSaleByIdSale(id);
        if (oldSale == null) {
            throw new NotFoundEntityException("Not found Sale by id " + id);
        }
        saleRepo.delete(oldSale);
    }
}
