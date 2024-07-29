package com.software.service;

import com.software.dto.ProviderDTO;
import com.software.model.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> getAllProviders();
    List<Provider> getProvidersByName(String name);
    Provider getProviderById(Long id);
    Provider createNewProvider(ProviderDTO providerDTO);
    Provider updateProvider(Long id, ProviderDTO providerDTO);
    void deleteProvider(Long id);
}
