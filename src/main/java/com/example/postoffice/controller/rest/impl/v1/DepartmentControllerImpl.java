package com.example.postoffice.controller.rest.impl.v1;

import com.example.postoffice.controller.rest.BaseController;
import com.example.postoffice.dto.department.RequestDepartmentDto;
import com.example.postoffice.dto.department.ResponseDepartmentDto;
import com.example.postoffice.entity.Department;
import com.example.postoffice.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentControllerImpl extends BaseController<Department,
        RequestDepartmentDto,
        ResponseDepartmentDto,
        DepartmentServiceImpl> {

    @Autowired
    protected DepartmentControllerImpl(DepartmentServiceImpl service) {
        super(service);
    }
}
