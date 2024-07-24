package com.software.controller;

import com.software.dto.ResponseMessage;
import com.software.dto.TypeProductDTO;
import com.software.mapper.TypeProductConvert;
import com.software.service.TypeProductService;
import com.software.utils.ErrorsInputMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/type")
public class TypeProductController {
    private final TypeProductService typeProductService;
    private final ErrorsInputMessage inputMessage;

//    Handle get all type contain type isActive true
    @GetMapping
    public ResponseEntity<ResponseMessage> doGet(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(ResponseMessage.builder()
                .status(true)
                .message("Get all type is successfully !")
                .data(typeProductService.getAllTypeProductIsActive(page, size))
                .build(), HttpStatus.OK);
    }

//    Handle get all type contain type isActive false
    @GetMapping("/hide-block")
    public ResponseEntity<ResponseMessage> doGetHideBlock(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(ResponseMessage.builder()
                .status(true)
                .message("Get all type is hide and block successfully !")
                .data(typeProductService.getAllTypeProduct(page, size))
                .build(), HttpStatus.OK);
    }

//    Handle get type product by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetById (@PathVariable Long id){
        return new ResponseEntity<>(ResponseMessage.builder()
                .status(true)
                .message("Get type product by id successfully !")
                .data(typeProductService.getTypeProductById(id))
                .build(), HttpStatus.OK);
    }

//    Handle create new type product
    @PostMapping
    public ResponseEntity<?> doCreate(@RequestBody @Valid TypeProductDTO typeProductDTO, BindingResult result){
        try{
            if(result.hasErrors()){// check input data errors
                return new ResponseEntity<>(ResponseMessage.builder()
                       .status(false)
                       .message("Create type product failed")
                       .data(inputMessage.getMessages(result))
                       .build(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(ResponseMessage.builder()
                    .status(true)
                    .message("Created new type product successfully !")
                    .data(typeProductService.saveTypeProduct(typeProductDTO))
                    .build(), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(ResponseMessage.builder()
                    .status(false)
                    .message("Error creating type product: "+e.getMessage())
                    .data(null)
                    .build(),HttpStatus.BAD_REQUEST);
        }
    }

//    Handle update type product
    @PutMapping("/{id}")
    public ResponseEntity<?> doUpdate(@PathVariable Long id, @RequestBody @Valid TypeProductDTO typeProductDTO, BindingResult result){
        try{
            if(result.hasErrors()){// check input data errors
                return new ResponseEntity<>(ResponseMessage.builder()
                       .status(false)
                       .message("Update type product failed")
                       .data(inputMessage.getMessages(result))
                       .build(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(ResponseMessage.builder()
                    .status(true)
                    .message("Updated type product successfully !")
                    .data(typeProductService.updateTypeProduct(id, typeProductDTO))
                    .build(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(ResponseMessage.builder()
                    .status(false)
                    .message("Error updating type product: "+e.getMessage())
                    .data(null)
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }

//    Handle delete type product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> doDelete(@PathVariable Long id){
        typeProductService.deleteTypeProduct(id);
        return new ResponseEntity<>(ResponseMessage.builder()
                .status(true)
                .message("Delete type product is successfully !")
                .build(),HttpStatus.NO_CONTENT);
    }

}
