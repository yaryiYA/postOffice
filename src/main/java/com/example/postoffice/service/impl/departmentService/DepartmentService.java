package com.example.postoffice.service.impl.departmentService;

import com.example.postoffice.entity.Department;
import com.example.postoffice.service.CommonService;

public interface DepartmentService extends CommonService<Department> {

    Department findByIndex(Integer indexDepartment);
}
