package com.software.repository;

import com.software.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepo extends JpaRepository<Provider, Long> {
    boolean existsByNameProvider(String name);
    List<Provider> getProviderByNameProviderContaining(String name);
    Provider getProviderByIdProvider(Long id);
}
