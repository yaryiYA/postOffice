package com.example.postoffice.service.impl.parcelService.impl;

import com.example.postoffice.entity.HistoryPoint;
import com.example.postoffice.entity.Parcel;
import com.example.postoffice.entity.enums.PointType;
import com.example.postoffice.repository.impl.ParcelRepository;
import com.example.postoffice.service.BaseService;

import com.example.postoffice.service.impl.departmentService.DepartmentService;
import com.example.postoffice.service.impl.departmentService.impl.DepartmentServiceImpl;
import com.example.postoffice.service.impl.parcelService.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Validated
public class ParcelServiceImpl
        extends BaseService<Parcel,
        ParcelRepository> implements ParcelService {


    private final DepartmentService departmentService;


    @Autowired
    public ParcelServiceImpl(ParcelRepository repository,
                             DepartmentService departmentService) {
        super(repository);
        this.departmentService = departmentService;
    }

    public Parcel registrationParcel(Parcel parcel, Integer startIndexDepartment) {
        departmentService.findByIndex(startIndexDepartment);
        return addEventParcel(parcel, startIndexDepartment, PointType.REGISTRATION);
    }

    public Parcel arriveAtDepartment(Long identifierParcel, Integer departmentIndex) {
        departmentService.findByIndex(departmentIndex);
        Optional<Parcel> parcelRep = super.repository.findById(identifierParcel);

        if (parcelRep.isPresent()) {
            addEventParcel(parcelRep.get(), departmentIndex, PointType.ARRIVED);
            return parcelRep.get();
        }

        throw new EntityNotFoundException("parcel not found");
    }

    public Parcel leaveDepartment(Long identifierParcel) {
        Optional<Parcel> parcelRep = super.repository.findById(identifierParcel);

        if (parcelRep.isPresent()) {
            Integer index = checkEndIndex(parcelRep.get());
            addEventParcel(parcelRep.get(), index, PointType.DEPARTURE);
            return parcelRep.get();
        }

        throw new EntityNotFoundException("parcel not found");
    }

    public Parcel deliveryToRecipient(Long identifierParcel) {
        Optional<Parcel> parcelRep = super.repository.findById(identifierParcel);

        if (parcelRep.isPresent()) {
            Integer index = checkEndIndex(parcelRep.get());
            addEventParcel(parcelRep.get(), index, PointType.DELIVERED);
            return parcelRep.get();
        }

        throw new EntityNotFoundException("parcel not found");
    }

    public List<HistoryPoint> getHistoryParcel(Long identifierParcel) {
        Optional<Parcel> parcelRep = super.repository.findById(identifierParcel);

        if (parcelRep.isPresent()) {
            return parcelRep.get().getHistoryPoints();
        }

        throw new EntityNotFoundException("parcel not found");
    }

    private Parcel addEventParcel(Parcel parcel, Integer departmentIndex, PointType pointType) {
        HistoryPoint historyPoint = HistoryPoint.builder()
                .pointType(pointType)
                .indexDepartment(departmentIndex).build();

        if (parcel.getHistoryPoints() == null) {
            parcel.setHistoryPoints(new ArrayList<>());
        }

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
