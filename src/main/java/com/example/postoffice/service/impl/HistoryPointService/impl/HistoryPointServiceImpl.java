package com.example.postoffice.service.impl.HistoryPointService.impl;

import com.example.postoffice.entity.HistoryPoint;
import com.example.postoffice.repository.impl.HistoryPointRepository;
import com.example.postoffice.service.BaseService;
import com.example.postoffice.service.impl.HistoryPointService.HistoryPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryPointServiceImpl extends BaseService<HistoryPoint,
        HistoryPointRepository> implements HistoryPointService {

    @Autowired
    public HistoryPointServiceImpl(HistoryPointRepository repository) {
        super(repository);
    }
}
