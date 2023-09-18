package com.example.postoffice.controller.rest.impl.v1.departmentController;

import com.example.postoffice.controller.rest.BaseController;
import com.example.postoffice.controller.rest.CommonController;
import com.example.postoffice.dto.department.RequestDepartmentDto;
import com.example.postoffice.dto.department.ResponseDepartmentDto;
import com.example.postoffice.entity.Department;
import com.example.postoffice.mapper.deprtament.DepartmentMapperImpl;
import com.example.postoffice.service.impl.departmentService.impl.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentControllerImpl extends BaseController<Department,
        RequestDepartmentDto,
        ResponseDepartmentDto,
        DepartmentServiceImpl,
        DepartmentMapperImpl>
        implements CommonController<Department,
                RequestDepartmentDto,
                ResponseDepartmentDto> {


    protected DepartmentControllerImpl(DepartmentServiceImpl service, DepartmentMapperImpl mapper) {
        super(service, mapper);
    }
}
