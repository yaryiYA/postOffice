package com.example.postoffice.service.impl;

import com.example.postoffice.dto.department.RequestDepartmentDto;
import com.example.postoffice.dto.department.ResponseDepartmentDto;
import com.example.postoffice.dto.parsel.ResponseParcelDto;
import com.example.postoffice.entity.Department;
import com.example.postoffice.entity.Parcel;
import com.example.postoffice.mapper.deprtament.DepartmentMapper;
import com.example.postoffice.repository.impl.DepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private DepartmentServiceImpl departmentService;
    @Mock
    private DepartmentMapper departmentMapper;

    private Department departmentBuild;
    private RequestDepartmentDto requestDepartmentDtoBuild;
    private ResponseDepartmentDto responseDepartmentDtoBuild;


    @BeforeEach
    public void setup() {
        departmentBuild = Department.builder()
                .name("test")
                .departmentAddress("test")
                .index(123456)
                .build();

        requestDepartmentDtoBuild = RequestDepartmentDto.builder()
                .name("test")
                .departmentAddress("test")
                .index(123456)
                .build();

        responseDepartmentDtoBuild = ResponseDepartmentDto.builder()
                .name("test")
                .departmentAddress("test")
                .index(123456)
                .build();
    }

    @Test
    public void departmentServiceImplCreate() {
        when(departmentMapper.toEntity(any())).thenReturn(departmentBuild);
        when(departmentRepository.save(any())).thenReturn(departmentBuild);
        when(departmentMapper.toResponse(any())).thenReturn(responseDepartmentDtoBuild);

        ResponseDepartmentDto responseDepartmentDto = departmentService.create(requestDepartmentDtoBuild);

        Assertions.assertThat(responseDepartmentDto).isNotNull();

        verify(departmentMapper).toEntity(any());
        verify(departmentRepository).save(any());
        verify(departmentMapper).toResponse(any());
    }

    @Test
    public void departmentServiceImplUpdate() {
        when(departmentRepository.findById(any())).thenReturn(Optional.ofNullable(departmentBuild));
        when(departmentMapper.toEntity(any())).thenReturn(departmentBuild);
        when(departmentRepository.save(any())).thenReturn(departmentBuild);
        when(departmentMapper.toResponse(any())).thenReturn(responseDepartmentDtoBuild);

        ResponseDepartmentDto responseDepartmentDto = departmentService.update(requestDepartmentDtoBuild, 1L);

        Assertions.assertThat(responseDepartmentDto).isNotNull();

        verify(departmentRepository).save(any());
        verify(departmentMapper).toEntity(any());
        verify(departmentRepository).save(any());
        verify(departmentMapper).toResponse(any());
    }

    @Test
    public void departmentServiceImplDelete() {
        when(departmentRepository.findById(any())).thenReturn(Optional.ofNullable(departmentBuild));
        doNothing().when(departmentRepository).delete(departmentBuild);

        assertAll(() -> departmentService.delete(any()));

        verify(departmentRepository).findById(any());
        verify(departmentRepository,times(1)).delete(any());
    }

    @Test
    public void departmentServiceImplFindById() {
        when(departmentRepository.findById(any())).thenReturn(Optional.ofNullable(departmentBuild));
        when(departmentMapper.toResponse(any())).thenReturn(responseDepartmentDtoBuild);

        ResponseDepartmentDto responseDepartmentDto = departmentService.findEntity(any()).get();

        Assertions.assertThat(responseDepartmentDto).isNotNull();

        verify(departmentRepository).findById(any());
        verify(departmentMapper).toResponse(any());

    }

    @Test
    public void departmentServiceImplFindAll() {
        Page<Department> departments = Mockito.mock();

        when(departmentRepository.findAll(any(Pageable.class))).thenReturn(departments);

        List<ResponseDepartmentDto> all = departmentService.findAll(1, 10);

        Assertions.assertThat(all).isNotNull();
    }


    @Test
    public void departmentServiceImplFindByIndex() {
        when(departmentRepository.findDepartmentByIndex(any())).thenReturn(Optional.ofNullable(departmentBuild));
        when(departmentMapper.toResponse(any())).thenReturn(responseDepartmentDtoBuild);

        ResponseDepartmentDto responseDepartmentDto = departmentService.findByIndex(any());

        Assertions.assertThat(responseDepartmentDto).isNotNull();

        verify(departmentRepository).findDepartmentByIndex(any());
        verify(departmentMapper).toResponse(any());
    }
}