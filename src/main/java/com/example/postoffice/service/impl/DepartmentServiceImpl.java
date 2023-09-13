package com.example.postoffice.service.impl;

import com.example.postoffice.entity.Department;


import com.example.postoffice.repository.impl.DepartmentRepository;
import com.example.postoffice.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
public class DepartmentServiceImpl
        extends BaseService<Department,
        DepartmentRepository> {

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository) {
        super(repository);
    }

    public Department findByIndex(Integer indexDepartment) {
        return super.repository.findDepartmentByIndex(indexDepartment)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
    }
}
