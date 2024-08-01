package com.software.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String nameProduct;
    private String ramProduct;
    private String romProduct;
    private String cpuProduct;
    private String cardProduct;
    private String screenProduct;
    private String descriptionProduct;
    private Long priceProduct;
    private Integer quantityProduct;
    private boolean isActive;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DetailPurchaseOrder> detailsPurchaseOrder = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "idBrand")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", referencedColumnName = "idSale")
    private Sale sale;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "product_type",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "type_id", referencedColumnName = "idTypeProduct")
    )
    private List<TypeProduct> typeProducts = new ArrayList<>();

}
