package com.software.service.serviceIMPL;

import com.software.dto.BrandDTO;
import com.software.model.Brand;
import com.software.repository.BrandRepo;
import com.software.service.BrandService;
import com.software.mapper.BrandConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceIMPL implements BrandService {
    private final BrandRepo brandRepo;
    private final BrandConvert brandConvert;

    @Override
    public Page<BrandDTO> getAllBrand(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Brand> pageBrand = brandRepo.findAllByActive(pageable);

        return pageBrand.map(brandConvert::covertToBrandDTO);// Result was converted to brand object dto
    }

    @Override
    public BrandDTO getBrandById(Long id) {
        Brand brand;
        try {
            brand = brandRepo.findById(id).orElseThrow(() -> new ClassNotFoundException("Could not find"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return brandConvert.covertToBrandDTO(brand);
    }

    @Override
    public BrandDTO getBrandByName(String name) {
        Brand brand = brandRepo.findBrandByBrandName(name);
        return brandConvert.covertToBrandDTO(brand);
    }

    @Override
    public BrandDTO saveBrand(BrandDTO brand) {
        Brand result = brandConvert.covertToBrand(brand);// Convert brand(DTO) to brand
        result.setActive(true); //Active brand
        result = brandRepo.save(result);
//      Return result
        return brandConvert.covertToBrandDTO(result);
    }

    @Override
    public BrandDTO updateBrand(Long id, BrandDTO newBrand) {
        try {
//          find brandOld needed to update
            Brand brandOld = brandRepo.findById(id).orElseThrow(() -> new ClassNotFoundException("Could not find"));

            if (brandOld != null) {// check brandOld is not null and update brandOld
                brandOld.setBrandName(newBrand.getBrandName());
                Brand result = brandRepo.save(brandOld);
                return brandConvert.covertToBrandDTO(result);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public void deleteBrand(Long id) {
        try {
//          find brandOld needed to update
            Brand brand = brandRepo.findById(id).orElseThrow(() -> new ClassNotFoundException("Could not find"));

            if (brand!= null) {// check brandOld is not null and unable isActive;
                brand.setActive(false);
                brandRepo.save(brand);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
