package com.example.postoffice.repository.impl;

import com.example.postoffice.entity.Parcel;
import com.example.postoffice.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface ParcelRepository extends CommonRepository<Parcel> {
    Optional<Parcel> findByIdentifier(Long identifier);



}
