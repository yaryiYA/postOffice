package com.example.postoffice.mapper.historyPoint;

import com.example.postoffice.dto.historyPoint.RequestHistoryPointDto;
import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
import com.example.postoffice.entity.HistoryPoint;
import com.example.postoffice.mapper.CommonMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoryPointMapper extends CommonMapper<HistoryPoint, RequestHistoryPointDto, ResponseHistoryPointDto> {


}
