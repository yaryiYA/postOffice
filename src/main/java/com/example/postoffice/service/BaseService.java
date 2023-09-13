package com.example.postoffice.service;


import com.example.postoffice.entity.AbstractEntity;

import com.example.postoffice.repository.CommonRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
public abstract class BaseService<E extends AbstractEntity,
        R extends CommonRepository<E>>
        implements CommonService<E> {

    protected final R   repository;


    @Autowired
    public BaseService(R repository) {
        this.repository = repository;
    }


    @Override
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public E findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("entity not found"));
    }

    @Override
    public E create(E entity) {
        return repository.save(entity);
    }

    @Override
    public E update(E entity, Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("not found " + id);
        }
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        E entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        repository.delete(entity);
    }


}
