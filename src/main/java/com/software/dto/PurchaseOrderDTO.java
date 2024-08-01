package com.software.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private Long provider;
    private List<DetailPurchaseDTO> details;
}
