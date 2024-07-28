package com.software.controller;

import com.software.dto.ResponseMessage;
import com.software.dto.SaleDTO;
import com.software.exception.DateException;
import com.software.exception.NotFoundEntityException;
import com.software.model.Sale;
import com.software.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sale")
public class SaleController {
    private final SaleService saleService;

    @GetMapping
    public ResponseEntity<ResponseMessage> getAllSale() {
        return ResponseEntity.ok(ResponseMessage.builder()
                .status(true)
                .message("Required get all sales successfully !")
                .data(saleService.gteAllSale())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getSaleById(@PathVariable Long id) {
        Sale saleById = saleService.getSaleById(id);
        if (saleById == null) {
            throw new NotFoundEntityException("Not found Sale by id: " + id);
        }
        return ResponseEntity.ok(ResponseMessage.builder()
                .status(true)
                .message("Required get sale by id successfully !")
                .data(saleService.getSaleById(id))
                .build());
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseMessage> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(ResponseMessage.builder()
                .status(true)
                .message("Required search sale by name successfully !")
                .data(saleService.getSaleByName(name))
                .build());
    }

    @GetMapping("/date")
    public ResponseEntity<ResponseMessage> getSaleByDate(@RequestBody Date start, @RequestBody Date end) {
//        Check input date not null
        if (start == null || end == null) {
            throw new DateException("Start date and end date must not be null !");
        }
//        Check input date not valid
        boolean isAfterDate = start.after(end);
        if (!isAfterDate) {
            throw new DateException("Start date must be before end date !");
        }
        return ResponseEntity.ok(ResponseMessage.builder()
                .status(true)
                .message("Required get sale by date successfully !")
                .data(saleService.getSaleByBetweenDate(start, end))
                .build());
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createSale(@RequestBody SaleDTO saleDTO) {
//        Check input date not null
        if (saleDTO.getStartDate() == null || saleDTO.getEndDate() == null) {
            throw new DateException("Start date and end date must not be null !");
        }
//        Check input date not valid
        boolean isAfterDate = saleDTO.getStartDate().before(saleDTO.getEndDate());
        if (!isAfterDate) {
            throw new DateException("Start date must be before end date !");
        }

        return ResponseEntity.ok(ResponseMessage.builder()
                .status(true)
                .message("Create sale successfully!")
                .data(saleService.createNewSale(saleDTO))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateSale(@PathVariable Long id,
                                                      @RequestBody SaleDTO saleDTO) {
        Sale saleOld = saleService.getSaleById(id);
        if (saleOld == null) {
            throw new NotFoundEntityException("Not found Sale by id: " + id);
        }
        return new ResponseEntity<>(ResponseMessage.builder()
                .status(true)
                .message("Request updated successfully !")
                .data(saleService.updateSale(id, saleDTO))
                .build(), HttpStatus.OK);
    }

}
