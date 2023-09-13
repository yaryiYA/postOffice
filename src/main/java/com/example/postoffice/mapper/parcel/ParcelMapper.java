package com.example.postoffice.mapper.parcel;

import com.example.postoffice.dto.parsel.RequestParcelDto;
import com.example.postoffice.dto.parsel.ResponseParcelDto;
import com.example.postoffice.entity.Parcel;
import com.example.postoffice.mapper.CommonMapper;
import com.example.postoffice.mapper.historyPoint.HistoryPointMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ParcelMapper extends CommonMapper<Parcel, RequestParcelDto, ResponseParcelDto> {

}
