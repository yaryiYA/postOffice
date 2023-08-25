package com.example.postoffice.repository.impl;

import com.example.postoffice.entity.Department;
import com.example.postoffice.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends CommonRepository<Department> {
    Optional<Department> findDepartmentByIndex(Integer index);
}
