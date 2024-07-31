package com.software.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProvider;
    private String nameProvider;
    private String phoneNumberProvider;
    private String emailProvider;

    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
}
