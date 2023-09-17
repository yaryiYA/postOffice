package com.example.postoffice.controller.rest.impl.v1.departmentController;

import com.example.postoffice.controller.rest.CommonController;
import com.example.postoffice.dto.department.RequestDepartmentDto;
import com.example.postoffice.dto.department.ResponseDepartmentDto;
import com.example.postoffice.entity.Department;

public interface DepartmentController extends CommonController<Department,
        RequestDepartmentDto,
        ResponseDepartmentDto> {
}
