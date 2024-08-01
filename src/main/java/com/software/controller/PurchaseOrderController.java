package com.software.controller;

import com.software.dto.PurchaseOrderDTO;
import com.software.dto.ResponseMessage;
import com.software.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/important")
@RequiredArgsConstructor
public class PurchaseOrderController {
    private final PurchaseOrderService purchaseOrderService;
    @PostMapping
    public ResponseEntity<ResponseMessage> create(@RequestBody PurchaseOrderDTO purchaseOrderDTO){
        return new ResponseEntity<>(ResponseMessage.builder()
                .status(true)
                .message("Import product is successfully !")
                .data(purchaseOrderService.createNewPurchaseOrder(purchaseOrderDTO))
                .build(), HttpStatus.CREATED);
    }
}
