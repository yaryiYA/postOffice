package com.example.postoffice.mapper.deprtament;

import com.example.postoffice.dto.department.RequestDepartmentDto;
import com.example.postoffice.dto.department.ResponseDepartmentDto;
import com.example.postoffice.entity.Department;
import com.example.postoffice.mapper.CommonMapper;
import com.example.postoffice.mapper.historyPoint.HistoryPointMapper;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring", uses = {HistoryPointMapper.class})
public interface DepartmentMapper extends CommonMapper<Department, RequestDepartmentDto, ResponseDepartmentDto> {

}
