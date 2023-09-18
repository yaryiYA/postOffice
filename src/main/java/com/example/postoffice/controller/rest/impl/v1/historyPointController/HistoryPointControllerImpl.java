package com.example.postoffice.controller.rest.impl.v1.historyPointController;

import com.example.postoffice.controller.rest.BaseController;
import com.example.postoffice.controller.rest.CommonController;
import com.example.postoffice.dto.historypoint.RequestHistoryPointDto;
import com.example.postoffice.dto.historypoint.ResponseHistoryPointDto;
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
        implements CommonController<HistoryPoint,
        RequestHistoryPointDto,
        ResponseHistoryPointDto> {
    @Autowired
    protected HistoryPointControllerImpl(HistoryPointServiceImpl service, HistoryPointMapperImpl mapper) {
        super(service, mapper);
    }
}
