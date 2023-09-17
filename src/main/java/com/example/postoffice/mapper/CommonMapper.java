package com.example.postoffice.mapper;

import com.example.postoffice.dto.AbstractRequestDto;
import com.example.postoffice.dto.AbstractResponseDto;
import com.example.postoffice.entity.AbstractEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;


public interface CommonMapper<E extends AbstractEntity, Q extends AbstractRequestDto, S extends AbstractResponseDto> {

    E toEntity(Q request);

    E partialUpdate(Q request, @MappingTarget E entity);

    S toResponse(E entity);
}
