package com.example.postoffice.service;

import com.example.postoffice.dto.AbstractRequestDto;
import com.example.postoffice.dto.AbstractResponseDto;
import com.example.postoffice.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommonService<E extends AbstractEntity,
        Q extends AbstractRequestDto,
        S extends AbstractResponseDto> {

    List<S> findAll(Integer pageNo, Integer pageSize);

    Optional<S> findEntity(Long id);
    S create(Q entity);
    S update(Q entity,Long id);
    void delete(Long id);

}
