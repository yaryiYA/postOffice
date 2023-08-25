package com.example.postoffice.service.impl;

import com.example.postoffice.dto.historyPoint.RequestHistoryPointDto;
import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
import com.example.postoffice.entity.HistoryPoint;
import com.example.postoffice.mapper.historyPoint.HistoryPointMapper;
import com.example.postoffice.repository.impl.HistoryPointRepository;
import com.example.postoffice.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryPointServiceImpl extends BaseService<HistoryPoint,
        RequestHistoryPointDto,
        ResponseHistoryPointDto,
        HistoryPointRepository,
        HistoryPointMapper> {

    @Autowired
    public HistoryPointServiceImpl(HistoryPointRepository repository, HistoryPointMapper mapper) {
        super(repository, mapper);
    }
}
