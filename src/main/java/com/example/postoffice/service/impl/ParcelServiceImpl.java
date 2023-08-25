package com.example.postoffice.service.impl;

import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
import com.example.postoffice.dto.parsel.RequestParcelDto;
import com.example.postoffice.dto.parsel.ResponseParcelDto;
import com.example.postoffice.entity.HistoryPoint;
import com.example.postoffice.entity.Parcel;
import com.example.postoffice.entity.enums.PointType;
import com.example.postoffice.mapper.historyPoint.HistoryPointMapper;
import com.example.postoffice.mapper.parcel.ParcelMapper;
import com.example.postoffice.repository.impl.DepartmentRepository;
import com.example.postoffice.repository.impl.ParcelRepository;
import com.example.postoffice.service.BaseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.time.LocalDateTime;
import java.util.List;



@Service
@Validated
public class ParcelServiceImpl
        extends BaseService<Parcel,
        RequestParcelDto, ResponseParcelDto,
        ParcelRepository,
        ParcelMapper> {


    private final DepartmentServiceImpl departmentService;
    private final HistoryPointMapper historyPointMapper;

    @Autowired
    public ParcelServiceImpl(ParcelRepository repository,
                             ParcelMapper mapper,
                             DepartmentRepository departmentRepository,
                             DepartmentServiceImpl departmentService,
                             HistoryPointMapper historyPointMapper) {
        super(repository, mapper);
        this.departmentService = departmentService;
        this.historyPointMapper = historyPointMapper;
    }


    public ResponseParcelDto registrationParcel(@Valid RequestParcelDto requestParcelDto, Integer startIndexDepartment) {
        departmentService.findByIndex(startIndexDepartment);
        Parcel entity = super.mapper.toEntity(requestParcelDto);
        Parcel parcel = addEventParcel(entity, startIndexDepartment, PointType.REGISTRATION);

        return super.mapper.toResponse(parcel);
    }

    public ResponseParcelDto arriveAtDepartment(Long identifierParcel, Integer departmentIndex) {
        departmentService.findByIndex(departmentIndex);
        Parcel parcelRep = super.repository.findByIdentifier(identifierParcel)
                .orElseThrow(() -> new EntityNotFoundException("Parcel not found"));
        Parcel parcel = addEventParcel(parcelRep, departmentIndex, PointType.ARRIVED);

        return super.mapper.toResponse(parcel);
    }

    public ResponseParcelDto leaveDepartment(Long identifierParcel) {
        Parcel parcelRep = super.repository.findByIdentifier(identifierParcel)
                .orElseThrow(() -> new EntityNotFoundException("Parcel not found"));
        Integer index = checkEndIndex(parcelRep);
        Parcel parcel = addEventParcel(parcelRep, index, PointType.DEPARTURE);

        return super.mapper.toResponse(parcel);
    }

    public ResponseParcelDto deliveryToRecipient(Long identifierParcel) {
        Parcel parcelRep = super.repository.findByIdentifier(identifierParcel)
                .orElseThrow(() -> new EntityNotFoundException("Parcel not found"));
        Integer index = checkEndIndex(parcelRep);
        Parcel parcel = addEventParcel(parcelRep, index, PointType.DELIVERED);

        return super.mapper.toResponse(parcel);
    }

    public List<ResponseHistoryPointDto> getHistoryParcel(Long identifierParcel) {
        Parcel parcel = super.repository.findByIdentifier(identifierParcel)
                .orElseThrow(() -> new EntityNotFoundException("Parcel not found"));

        return parcel.getHistoryPoints().stream()
                .map(historyPointMapper::toResponse).toList();
    }


    private Parcel addEventParcel(Parcel parcel, Integer departmentIndex, PointType pointType) {
        HistoryPoint historyPoint = new HistoryPoint(pointType, LocalDateTime.now(), departmentIndex);
        parcel.getHistoryPoints().add(historyPoint);

        return super.repository.save(parcel);
    }

    private Integer checkEndIndex(Parcel parcel) {
        List<HistoryPoint> historyPoints = parcel.getHistoryPoints();

        return historyPoints.get(historyPoints.size() - 1).getIndexDepartment();
    }

}
