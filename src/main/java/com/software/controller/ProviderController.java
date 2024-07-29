package com.software.controller;

import com.software.dto.ProviderDTO;
import com.software.dto.ResponseMessage;
import com.software.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/provider")
public class ProviderController {
    private final ProviderService providerService;

    @GetMapping
    public ResponseEntity<ResponseMessage> getAllProviders(){
        return ResponseEntity.ok(ResponseMessage.builder()
                .status(true)
                .message("Get all providers successfully!")
                .data(providerService.getAllProviders())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getProviderById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseMessage.builder()
                .status(true)
                .message("Get provider by id successfully!")
                .data(providerService.getProviderById(id))
                .build());
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseMessage> searchProviderByName(@RequestParam String name){
        return ResponseEntity.ok(ResponseMessage.builder()
                .status(true)
                .message("Search provider by name successfully!")
                .data(providerService.getProvidersByName(name))
                .build());
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createProvider(@RequestBody ProviderDTO providerDTO){
        return new ResponseEntity<>(ResponseMessage.builder()
                .status(true)
                .message("Create provider successfully!")
                .data(providerService.createNewProvider(providerDTO))
                .build(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateProvider(@PathVariable Long id, @RequestBody ProviderDTO providerDTO){
        return ResponseEntity.ok(ResponseMessage.builder()
                .status(true)
                .message("Update provider successfully!")
                .data(providerService.updateProvider(id, providerDTO))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteProvider(@PathVariable Long id){
        providerService.deleteProvider(id);
        return new ResponseEntity<>(ResponseMessage.builder()
                .status(true)
                .message("Delete provider successfully!")
                .build(), HttpStatus.NO_CONTENT);
    }
}
