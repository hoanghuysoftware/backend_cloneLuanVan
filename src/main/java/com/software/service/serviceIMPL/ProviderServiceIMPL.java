package com.software.service.serviceIMPL;

import com.software.dto.ProviderDTO;
import com.software.exception.NameExistsException;
import com.software.exception.NotFoundEntityException;
import com.software.model.Provider;
import com.software.repository.ProviderRepo;
import com.software.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderServiceIMPL implements ProviderService {
    private final ProviderRepo providerRepo;

    @Override
    public List<Provider> getAllProviders() {
        List<Provider> result = providerRepo.findAll();
        if (result.isEmpty()) {
            throw new NotFoundEntityException("Not found providers with ");
        }
        return result;
    }

    @Override
    public List<Provider> getProvidersByName(String name) {
//        Check name is not empty
        if(name == null || name.isEmpty()){
            throw new RuntimeException("Name must not be null !");
        }
//        Check result is not empty
        List<Provider> result = providerRepo.getProviderByNameProviderContaining(name);
        if (result == null || result.isEmpty()) {
            throw new NotFoundEntityException("Not found providers with name '" + name + "'");
        }
        return result;
    }

    @Override
    public Provider getProviderById(Long id) {
        Provider provider = providerRepo.getProviderByIdProvider(id);
        if (provider == null) {
            throw new NotFoundEntityException("Not found provider with id " + id);
        }
        return provider;
    }

    @Override
    public Provider createNewProvider(ProviderDTO providerDTO) {
//        Check providerDTO not null
        if (providerDTO == null) {
            throw new NullPointerException("ProviderDTO cannot be null");
        }
//        Check name providerDTO not existing
        boolean isNameExist = providerRepo.existsByNameProvider(providerDTO.getNameProvider());
        if (isNameExist) {
            throw new NameExistsException("Provider name already exists");
        }

        Provider newProvider = Provider.builder()
                .nameProvider(providerDTO.getNameProvider())
                .emailProvider(providerDTO.getEmailProvider())
                .phoneNumberProvider(providerDTO.getPhoneNumberProvider())
                .build();

        return providerRepo.save(newProvider);
    }

    @Override
    public Provider updateProvider(Long id, ProviderDTO providerDTO) {
//        Check provider was searched by id
        Provider oldProvider = providerRepo.getProviderByIdProvider(id);
        if (oldProvider == null) {
            throw new NotFoundEntityException("Not found provider with id " + id);
        }
//        Check name providerDTO not existing
        boolean isNameExist = providerRepo.existsByNameProvider(providerDTO.getNameProvider());
        if (isNameExist) {
            throw new NameExistsException("Provider name already exists");
        }
        oldProvider.setNameProvider(providerDTO.getNameProvider());
        oldProvider.setEmailProvider(providerDTO.getEmailProvider());
        oldProvider.setPhoneNumberProvider(providerDTO.getPhoneNumberProvider());
        return providerRepo.save(oldProvider);
    }

    @Override
    public void deleteProvider(Long id) {
        Provider oldProvider = providerRepo.getProviderByIdProvider(id);
        if (oldProvider == null) {
            throw new NotFoundEntityException("Not found provider with id " + id);
        }
        providerRepo.delete(oldProvider);
    }
}
