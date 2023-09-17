package com.example.postoffice.service.impl.parcelService;

import com.example.postoffice.entity.HistoryPoint;
import com.example.postoffice.entity.Parcel;
import com.example.postoffice.service.CommonService;


import java.util.List;

public interface ParcelService extends CommonService<Parcel> {

    Parcel registrationParcel(Parcel parcel, Integer startIndexDepartment);

    Parcel arriveAtDepartment(Long identifierParcel, Integer departmentIndex);

    Parcel leaveDepartment(Long identifierParcel);

    Parcel deliveryToRecipient(Long identifierParcel);

    List<HistoryPoint> getHistoryParcel(Long identifierParcel);
}
