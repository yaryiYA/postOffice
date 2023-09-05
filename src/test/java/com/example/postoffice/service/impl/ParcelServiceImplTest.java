package com.example.postoffice.service.impl;

import com.example.postoffice.dto.department.ResponseDepartmentDto;
import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
import com.example.postoffice.dto.parsel.RequestParcelDto;
import com.example.postoffice.dto.parsel.ResponseParcelDto;

import com.example.postoffice.entity.HistoryPoint;
import com.example.postoffice.entity.Parcel;
import com.example.postoffice.entity.enums.ParcelType;

import com.example.postoffice.mapper.historyPoint.HistoryPointMapper;
import com.example.postoffice.mapper.parcel.ParcelMapper;
import com.example.postoffice.repository.impl.ParcelRepository;
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


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ParcelServiceImplTest {
    @Mock
    private ParcelRepository parcelRepository;
    @Mock
    private ParcelMapper parcelMapper;

    @Mock
    HistoryPointMapper historyPointMapper;
    @Mock
    private DepartmentServiceImpl departmentService;
    @InjectMocks
    private ParcelServiceImpl parcelService;

    private Parcel parcelBuild;
    private RequestParcelDto requestParcelDtoBuild;
    private ResponseParcelDto responseParcelDtoBuild;


    @BeforeEach
    public void setup() {
        parcelBuild = Parcel.builder()
                .parcelType(ParcelType.LETTER)
                .recipientIndex(123456)
                .recipientAddress("test")
                .firstName("ivan")
                .lastName("ivanov")
                .historyPoints(new ArrayList<>())
                .build();

        requestParcelDtoBuild = RequestParcelDto.builder()
                .parcelType(ParcelType.LETTER)
                .recipientIndex(123456)
                .recipientAddress("test")
                .firstName("ivan")
                .lastName("ivanov")
                .build();

        responseParcelDtoBuild = ResponseParcelDto.builder()
                .parcelType(ParcelType.LETTER)
                .recipientIndex(123456)
                .recipientAddress("test")
                .firstName("ivan")
                .lastName("ivanov")
                .historyPoints(new ArrayList<>())
                .build();
    }

    @Test
    public void ParcelServiceImplCreate() {
        when(parcelMapper.toEntity(any())).thenReturn(parcelBuild);
        when(parcelRepository.save(any())).thenReturn(parcelBuild);
        when(parcelMapper.toResponse(any())).thenReturn(responseParcelDtoBuild);

        ResponseParcelDto responseParcelDto = parcelService.create(requestParcelDtoBuild);

        Assertions.assertThat(responseParcelDto).isNotNull();

        verify(parcelMapper).toEntity(requestParcelDtoBuild);
        verify(parcelRepository).save(any());
        verify(parcelMapper).toResponse(parcelBuild);
    }

    @Test
    public void ParcelServiceImplFindAll() {
        Page<Parcel> parcels = Mockito.mock();

        when(parcelRepository.findAll(any(Pageable.class))).thenReturn(parcels);

        List<ResponseParcelDto> all = parcelService.findAll(1, 10);

        Assertions.assertThat(all).isNotNull();

    }

    @Test
    public void ParcelServiceFindById() {
        Long identifierParcel = 1L;
        when(parcelRepository.findById(identifierParcel)).thenReturn(Optional.of((parcelBuild)));
        when(parcelMapper.toResponse(any())).thenReturn(responseParcelDtoBuild);

        ResponseParcelDto responseParcelDto = parcelService.findEntity(identifierParcel).get();

        Assertions.assertThat(responseParcelDto).isNotNull();

        verify(parcelRepository, times(1)).findById(identifierParcel);
        verify(parcelMapper).toResponse(any());
    }

    @Test
    public void ParcelServiceUpdate() {

        when(parcelRepository.findById(parcelBuild.getIdentifier())).thenReturn(Optional.of(parcelBuild));
        when(parcelMapper.toEntity(any())).thenReturn(parcelBuild);
        when(parcelMapper.toResponse(any())).thenReturn(responseParcelDtoBuild);

        ResponseParcelDto update = parcelService.update(requestParcelDtoBuild, parcelBuild.getIdentifier());

        Assertions.assertThat(update).isNotNull();

        verify(parcelRepository).findById(parcelBuild.getIdentifier());
        verify(parcelMapper).toEntity(any());
        verify(parcelMapper).toResponse(any());

    }

    @Test
    public void ParcelServiceDelete() {
        when(parcelRepository.findById(parcelBuild.getIdentifier())).thenReturn(Optional.ofNullable(parcelBuild));
        doNothing().when(parcelRepository).delete(parcelBuild);

        assertAll(() -> parcelService.delete(parcelBuild.getIdentifier()));

        verify(parcelRepository,times(1)).findById(parcelBuild.getIdentifier());
    }


    @Test
    void registrationParcel() {
        Integer startIndexDepartment = 123456;

        when(departmentService.findByIndex(startIndexDepartment)).thenReturn(new ResponseDepartmentDto());
        when(parcelMapper.toEntity(requestParcelDtoBuild)).thenReturn(parcelBuild);
        when(parcelRepository.save(any())).thenReturn(parcelBuild);
        when(parcelMapper.toResponse(any())).thenReturn(responseParcelDtoBuild);

        ResponseParcelDto responseParcelDto = parcelService.registrationParcel(requestParcelDtoBuild, startIndexDepartment);

        verify(departmentService).findByIndex(startIndexDepartment);
        verify(parcelMapper).toEntity(requestParcelDtoBuild);
        verify(parcelRepository).save(parcelBuild);
        verify(parcelMapper).toResponse(parcelBuild);
        Assertions.assertThat(responseParcelDto).isNotNull();
    }

    @Test
    void arriveAtDepartment() {
        Long identifierParcel = 1L;
        Integer departmentIndex = 123456;

        when(departmentService.findByIndex(departmentIndex)).thenReturn(new ResponseDepartmentDto());
        when(parcelRepository.findById(identifierParcel)).thenReturn(Optional.ofNullable(parcelBuild));
        when(parcelRepository.save(any())).thenReturn(parcelBuild);
        when(parcelMapper.toResponse(any())).thenReturn(responseParcelDtoBuild);

        ResponseParcelDto responseParcelDto = parcelService.arriveAtDepartment(identifierParcel, departmentIndex);

        verify(departmentService).findByIndex(departmentIndex);
        verify(parcelRepository, times(1)).findById(identifierParcel);
        verify(parcelRepository).save(parcelBuild);
        verify(parcelMapper).toResponse(parcelBuild);
        Assertions.assertThat(responseParcelDto).isNotNull();
    }

    @Test
    void leaveDepartment() {
        Long identifierParcel = 1L;
        Integer departmentIndex = 123456;

        when(departmentService.findByIndex(departmentIndex)).thenReturn(new ResponseDepartmentDto());
        when(parcelRepository.findById(identifierParcel)).thenReturn(Optional.ofNullable(parcelBuild));
        when(parcelRepository.save(any())).thenReturn(parcelBuild);
        when(parcelMapper.toResponse(any())).thenReturn(responseParcelDtoBuild);

        ResponseParcelDto responseParcelDto = parcelService.arriveAtDepartment(identifierParcel, departmentIndex);

        verify(departmentService).findByIndex(departmentIndex);
        verify(parcelRepository, times(1)).findById(identifierParcel);
        verify(parcelRepository).save(parcelBuild);
        verify(parcelMapper).toResponse(parcelBuild);
        Assertions.assertThat(responseParcelDto).isNotNull();
    }

    @Test
    void deliveryToRecipient() {

        Long identifierParcel = 1L;
        Integer departmentIndex = 123456;

        when(departmentService.findByIndex(departmentIndex)).thenReturn(new ResponseDepartmentDto());
        when(parcelRepository.findById(identifierParcel)).thenReturn(Optional.ofNullable(parcelBuild));
        when(parcelRepository.save(any())).thenReturn(parcelBuild);
        when(parcelMapper.toResponse(any())).thenReturn(responseParcelDtoBuild);

        ResponseParcelDto responseParcelDto = parcelService.arriveAtDepartment(identifierParcel, departmentIndex);

        verify(departmentService).findByIndex(departmentIndex);
        verify(parcelRepository, times(1)).findById(identifierParcel);
        verify(parcelRepository).save(parcelBuild);
        verify(parcelMapper).toResponse(parcelBuild);
        Assertions.assertThat(responseParcelDto).isNotNull();
    }

    @Test
    void getHistoryParcel() {
        Long identifierParcel = 1L;

        parcelBuild.getHistoryPoints().add(new HistoryPoint());
        parcelBuild.getHistoryPoints().add(new HistoryPoint());

        when(parcelRepository.findById(identifierParcel)).thenReturn(Optional.ofNullable(parcelBuild));
        when(historyPointMapper.toResponse(any())).thenReturn(new ResponseHistoryPointDto());
        when(historyPointMapper.toResponse(any())).thenReturn(new ResponseHistoryPointDto());

        List<ResponseHistoryPointDto> historyParcel = parcelService.getHistoryParcel(identifierParcel);

        Assertions.assertThat(historyParcel).isNotNull();
        Assertions.assertThat(historyParcel.size()).isEqualTo(2);

        verify(parcelRepository, times(1)).findById(identifierParcel);
        verify(historyPointMapper, times(2)).toResponse(any());
    }
}