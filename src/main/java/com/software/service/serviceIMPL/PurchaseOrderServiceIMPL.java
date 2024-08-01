package com.software.service.serviceIMPL;

import com.software.dto.DetailPurchaseDTO;
import com.software.dto.ProductDTO;
import com.software.dto.PurchaseOrderDTO;
import com.software.model.*;
import com.software.repository.BrandRepo;
import com.software.repository.ProviderRepo;
import com.software.repository.PurchaseOrderRepo;
import com.software.repository.TypeProductRepo;
import com.software.service.PurchaseOrderService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceIMPL implements PurchaseOrderService {
    private final PurchaseOrderRepo purchaseOrderRepo;
    private final ProviderRepo providerRepo;
    private final BrandRepo brandRepo;
    private final TypeProductRepo typeProductRepo;
    @Override
    @Transactional
    public PurchaseOrder createNewPurchaseOrder(PurchaseOrderDTO purchaseOrder) {
//      Check entity exists
        Provider provider = providerRepo.getProviderByIdProvider(purchaseOrder.getProvider());
        if (provider == null) {
            throw new EntityExistsException("Provider not found by id: "+ purchaseOrder.getProvider());
        }
//      Create default purchase order
        PurchaseOrder newPurchaseOrder = PurchaseOrder.builder()
                .datePurchaseOrder(new Date())
                .provider(provider)
                .build();
//        Create detail for the purchase order contain (quantity, price and product)
        List<DetailPurchaseOrder> details = new ArrayList<>();

        for(DetailPurchaseDTO item: purchaseOrder.getDetails()){
            Product product = createProduct(item.getProduct());
            product.setQuantityProduct(item.getNumber());
            product.setPriceProduct(item.getPrice());
            DetailPurchaseOrder newDetails = DetailPurchaseOrder.builder()
                    .number(item.getNumber())
                    .price(item.getPrice())
                    .purchaseOrder(newPurchaseOrder)
                    .product(product)
                    .build();
            details.add(newDetails);
        }
        newPurchaseOrder.setDetailsPurchaseOrderList(details);
        return purchaseOrderRepo.save(newPurchaseOrder);
    }

    public Product createProduct(ProductDTO productDTO){
        Brand brand = brandRepo.findById(productDTO.getBrand())
                .orElseThrow(() -> new EntityExistsException("Not found brand with id " + productDTO.getBrand()));
        List<TypeProduct> typeProducts = new ArrayList<>();
        for(Long idType: productDTO.getTypeProduct()){
            TypeProduct typeProduct = typeProductRepo.getTypeProductByIdTypeProduct(idType);
            typeProducts.add(typeProduct);
        }

        return Product.builder()
                .nameProduct(productDTO.getName())
                .ramProduct(productDTO.getRam())
                .romProduct(productDTO.getRom())
                .cpuProduct(productDTO.getCpu())
                .cardProduct(productDTO.getCard())
                .screenProduct(productDTO.getScreen())
                .descriptionProduct(productDTO.getDescription())
                .isActive(true)
                .brand(brand)
                .typeProducts(typeProducts)
                .build();
    }
}
