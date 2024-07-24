package com.software.controller;

import com.software.dto.BrandDTO;
import com.software.dto.ResponseMessage;
import com.software.service.BrandService;
import com.software.mapper.BrandConvert;
import com.software.utils.ErrorsInputMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brand")
public class BrandController {
    private final ErrorsInputMessage inputMessage;
    private final BrandService brandService;

//    Handle get all brand information
    @GetMapping
    public ResponseEntity<ResponseMessage> doGetAll (@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(ResponseMessage.builder()
                .status(true)
                .message("Get all brand information successfully !")
                .data(brandService.getAllBrand(page, size))
                .build(), HttpStatus.OK);
    }

//    Handle get brand by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetById (@PathVariable Long id){
        return new ResponseEntity<>(ResponseMessage.builder()
                .status(true)
                .message("Get brand by id successfully !")
                .data(brandService.getBrandById(id))
                .build(), HttpStatus.OK);
    }

//    Handel create new brand
    @PostMapping
    public ResponseEntity<ResponseMessage> doCreate (@RequestBody @Valid BrandDTO brandDTO, BindingResult result){
        try{
//          Handle create brand request when the brand input data false
            if (result.hasErrors()){
                return new ResponseEntity<>(ResponseMessage.builder()
                        .status(false)
                        .message("Create brand failed")
                        .data(inputMessage.getMessages(result))
                        .build(), HttpStatus.BAD_REQUEST);
            }
//          Handle create brand flow control normal
//            Save brand into database
            return new ResponseEntity<>(ResponseMessage.builder()
                    .status(true)
                    .message("Create brand successfully!")
                    .data(brandService.saveBrand(brandDTO))
                    .build(), HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(ResponseMessage.builder()
                    .status(false)
                    .message("Create brand failed")
                    .data(e.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }

//    Handle update request brand
    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> doUpdate(@PathVariable Long id, @RequestBody @Valid BrandDTO brandDTO, BindingResult result){
        //Check data input
        if(result.hasErrors()){
            return new ResponseEntity<>(ResponseMessage.builder()
                    .status(false)
                    .message("Update request brand " +id+" failed")
                    .data(inputMessage.getMessages(result))
                    .build(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ResponseMessage.builder()
                .status(true)
                .message("Request updated successfully !")
                .data(brandService.updateBrand(id, brandDTO))
                .build(), HttpStatus.OK);

    }

//    Handle delete request
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> doDelete (@PathVariable Long id){
        brandService.deleteBrand(id);
        return new ResponseEntity<>(ResponseMessage.builder()
                .status(true)
                .message("Delete request successfully !")
                .data("")
                .build(), HttpStatus.NO_CONTENT);
    }
}
