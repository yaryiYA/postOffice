package com.example.postoffice.controller.rest.impl.v1.historyPointController.impl;

import com.example.postoffice.controller.rest.BaseController;
import com.example.postoffice.controller.rest.impl.v1.historyPointController.HistoryPointController;
import com.example.postoffice.dto.historyPoint.RequestHistoryPointDto;
import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
import com.example.postoffice.entity.HistoryPoint;
import com.example.postoffice.mapper.historyPoint.HistoryPointMapperImpl;
import com.example.postoffice.service.impl.HistoryPointService.impl.HistoryPointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/historyPoint")
public class HistoryPointControllerImpl extends BaseController<HistoryPoint,
        RequestHistoryPointDto,
        ResponseHistoryPointDto,
        HistoryPointServiceImpl,
        HistoryPointMapperImpl>
implements HistoryPointController {
    @Autowired
    protected HistoryPointControllerImpl(HistoryPointServiceImpl service, HistoryPointMapperImpl mapper) {
        super(service, mapper);
    }
}
