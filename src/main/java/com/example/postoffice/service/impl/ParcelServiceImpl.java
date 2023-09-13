package com.example.postoffice.service.impl;

import com.example.postoffice.entity.HistoryPoint;
import com.example.postoffice.entity.Parcel;
import com.example.postoffice.entity.enums.PointType;
import com.example.postoffice.repository.impl.ParcelRepository;
import com.example.postoffice.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Validated
public class ParcelServiceImpl
        extends BaseService<Parcel,
        ParcelRepository> {


    private final DepartmentServiceImpl departmentService;
    private final HistoryPointServiceImpl historyPointService;
    @Autowired
    public ParcelServiceImpl(ParcelRepository repository,
                             DepartmentServiceImpl departmentService, HistoryPointServiceImpl historyPointService) {
        super(repository);
        this.departmentService = departmentService;

        this.historyPointService = historyPointService;
    }



    public Parcel registrationParcel(@Valid Parcel parcel, Integer startIndexDepartment) {
        departmentService.findByIndex(startIndexDepartment);
        return addEventParcel(parcel, startIndexDepartment, PointType.REGISTRATION);
    }

    public Parcel arriveAtDepartment(Long identifierParcel, Integer departmentIndex) {
        departmentService.findByIndex(departmentIndex);
        Parcel parcelRep = super.repository.findById(identifierParcel)
                .orElseThrow(() -> new EntityNotFoundException("Parcel not found"));

        return addEventParcel(parcelRep, departmentIndex, PointType.ARRIVED);
    }

    public Parcel leaveDepartment(Long identifierParcel) {
        Parcel parcel = super.repository.findById(identifierParcel)
                .orElseThrow(() -> new EntityNotFoundException("Parcel not found"));
        Integer index = checkEndIndex(parcel);

        return addEventParcel(parcel, index, PointType.DEPARTURE);
    }

    public Parcel deliveryToRecipient(Long identifierParcel) {
        Parcel parcel = super.repository.findById(identifierParcel)
                .orElseThrow(() -> new EntityNotFoundException("Parcel not found"));
        Integer index = checkEndIndex(parcel);

        return addEventParcel(parcel, index, PointType.DELIVERED);
    }

    public List<HistoryPoint> getHistoryParcel(Long identifierParcel) {
        Parcel parcel = super.repository.findById(identifierParcel)
                .orElseThrow(() -> new EntityNotFoundException("Parcel not found"));

        return parcel.getHistoryPoints();
    }

    private Parcel addEventParcel(Parcel parcel, Integer departmentIndex, PointType pointType) {
        HistoryPoint historyPoint = new HistoryPoint(pointType, LocalDateTime.now(), departmentIndex);
//        historyPointService.create(historyPoint);

        List<HistoryPoint> historyPoints = parcel.getHistoryPoints();
        historyPoints.add(historyPoint);
        parcel.setHistoryPoints(historyPoints);

        return repository.save(parcel);
    }

    private Integer checkEndIndex(Parcel parcel) {
        List<HistoryPoint> historyPoints = parcel.getHistoryPoints();

        return historyPoints.get(historyPoints.size() - 1).getIndexDepartment();
    }

}
