package com.example.postoffice.service;

import com.example.postoffice.dto.AbstractRequestDto;
import com.example.postoffice.dto.AbstractResponseDto;
import com.example.postoffice.entity.AbstractEntity;
import com.example.postoffice.mapper.CommonMapper;
import com.example.postoffice.repository.CommonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public abstract class BaseService<E extends AbstractEntity,
        Q extends AbstractRequestDto,
        S extends AbstractResponseDto,
        R extends CommonRepository<E>,
        M extends CommonMapper<E, Q, S>>
        implements CommonService<E, Q, S> {

    protected final R repository;
    protected final M mapper;


    @Autowired
    public BaseService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<S> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public Optional<S> findEntity(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse);
    }

    @Override
    public S create(Q entity) {
        E object = mapper.toEntity(entity);
        repository.save(object);
        return mapper.toResponse(object);
    }

    @Override
    public S update(Q entity, UUID id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("not found " + id);
        }
        E object = mapper.toEntity(entity);
        repository.save(object);

        return mapper.toResponse(object);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
