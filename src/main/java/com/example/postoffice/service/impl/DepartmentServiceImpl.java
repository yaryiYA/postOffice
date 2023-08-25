package com.example.postoffice.service.impl;

import com.example.postoffice.dto.department.RequestDepartmentDto;
import com.example.postoffice.dto.department.ResponseDepartmentDto;
import com.example.postoffice.entity.Department;

import com.example.postoffice.mapper.deprtament.DepartmentMapper;
import com.example.postoffice.repository.impl.DepartmentRepository;
import com.example.postoffice.service.BaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl
        extends BaseService<Department,
        RequestDepartmentDto,
        ResponseDepartmentDto,
        DepartmentRepository,
        DepartmentMapper> {

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository, DepartmentMapper mapper) {
        super(repository, mapper);
    }

    ResponseDepartmentDto findByIndex(Integer indexDepartment) {
        Department department = super.repository.findDepartmentByIndex(indexDepartment)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        return super.mapper.toResponse(department);
    }


}
