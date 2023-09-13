package com.example.postoffice.controller.rest;

import com.example.postoffice.dto.AbstractRequestDto;
import com.example.postoffice.dto.AbstractResponseDto;
import com.example.postoffice.entity.AbstractEntity;
import com.example.postoffice.entity.enums.EntitySort;
import com.example.postoffice.mapper.CommonMapper;
import com.example.postoffice.service.CommonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public abstract class BaseController<E extends AbstractEntity,
        Q extends AbstractRequestDto,
        S extends AbstractResponseDto,
        V extends CommonService<E>,
        M extends CommonMapper<E, Q, S>>
        implements CommonController<E, Q, S> {

    protected final V service;
    protected final M mapper;

    protected BaseController(V service, M mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Page<S>> getAll(Integer offset, Integer limit, EntitySort sort) {

        List<S> list = service.findAll(PageRequest.of(offset, limit, sort.getSortValue())).stream()
                .map(mapper::toResponse).toList();
        return ResponseEntity.ok(new PageImpl<>(list));
    }

    @Override
    public ResponseEntity<?> getEntity(Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.findEntity(id)));
    }

    @Override
    public ResponseEntity<?> createEntity(Q requestEntity) {
        E entity = mapper.toEntity(requestEntity);
        S responseEntity = mapper.toResponse(service.create(entity));
        return ResponseEntity.ok(responseEntity);
    }

    @Override
    public ResponseEntity<?> updateEntity(Q requestEntity, Long id) {
        E entity = mapper.toEntity(requestEntity);
        S responseEntity = mapper.toResponse(service.update(entity, id));
        return ResponseEntity.ok(responseEntity);
    }

    @Override
    public ResponseEntity<String> deleteEntity(Long id) {
        service.delete(id);
        return ResponseEntity.ok("Entity was deleted");
    }
}
