package com.example.postoffice.service.impl;

import com.example.postoffice.entity.HistoryPoint;
import com.example.postoffice.repository.impl.HistoryPointRepository;
import com.example.postoffice.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryPointServiceImpl extends BaseService<HistoryPoint,
        HistoryPointRepository> {

    @Autowired
    public HistoryPointServiceImpl(HistoryPointRepository repository) {
        super(repository);
    }
}
