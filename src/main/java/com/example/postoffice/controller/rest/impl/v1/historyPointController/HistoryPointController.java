package com.example.postoffice.controller.rest.impl.v1.historyPointController;

import com.example.postoffice.controller.rest.CommonController;
import com.example.postoffice.dto.historyPoint.RequestHistoryPointDto;
import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
import com.example.postoffice.entity.HistoryPoint;

public interface HistoryPointController extends CommonController<HistoryPoint,
        RequestHistoryPointDto,
        ResponseHistoryPointDto> {
}
