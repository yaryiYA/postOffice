package com.example.postoffice.controller.rest;

import com.example.postoffice.dto.AbstractRequestDto;
import com.example.postoffice.dto.AbstractResponseDto;
import com.example.postoffice.entity.AbstractEntity;
import com.example.postoffice.service.CommonService;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.UUID;

public abstract class BaseController<E extends AbstractEntity,
        Q extends AbstractRequestDto,
        S extends AbstractResponseDto,
        V extends CommonService<E, Q, S>>
        implements CommonController<E, Q, S> {

    protected final V service;

    protected BaseController(V service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<S>> getAll(Integer pageNo, Integer pageSize) {
        return ResponseEntity.ok(service.findAll(pageNo,pageSize));
    }

    @Override
    public ResponseEntity<?> getEntity(Long id) {
        return ResponseEntity.ok(service.findEntity(id));
    }

    @Override
    public ResponseEntity<?> createEntity(Q entity) {
        return ResponseEntity.ok(service.create(entity));
    }

    @Override
    public ResponseEntity<?> updateEntity( Q entity, Long id) {
        return ResponseEntity.ok(service.update(entity,id));
    }

    @Override
    public ResponseEntity<String> deleteEntity(Long id) {
        service.delete(id);
        return ResponseEntity.ok("Entity was deleted");
    }
}
