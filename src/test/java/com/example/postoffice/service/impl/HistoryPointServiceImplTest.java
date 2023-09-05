package com.example.postoffice.service.impl;

import com.example.postoffice.dto.historyPoint.RequestHistoryPointDto;
import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
import com.example.postoffice.entity.HistoryPoint;
import com.example.postoffice.mapper.historyPoint.HistoryPointMapper;
import com.example.postoffice.repository.impl.HistoryPointRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class HistoryPointServiceImplTest {

    @Mock
    private HistoryPointRepository historyPointRepository;
    @InjectMocks
    private HistoryPointServiceImpl historyPointService;
    @Mock
    private HistoryPointMapper historyPointMapper;

    private HistoryPoint historyPointBuild;
    private RequestHistoryPointDto requestHistoryPointDtoBuild;
    private ResponseHistoryPointDto responseHistoryPointDtoBuild;

    @BeforeEach
    public void setup() {
        historyPointBuild = HistoryPoint.builder()
                .indexDepartment(123456)
                .appointmentDate(LocalDateTime.now())
                .build();

        requestHistoryPointDtoBuild = RequestHistoryPointDto.builder()
                .indexDepartment(123456)
                .appointmentDate(LocalDateTime.now())
                .build();

        responseHistoryPointDtoBuild = ResponseHistoryPointDto.builder()
                .indexDepartment(123456)
                .appointmentDate(LocalDateTime.now())
                .build();
    }

    @Test
    public void historyPointServiceImplCreate() {
        when(historyPointMapper.toEntity(any())).thenReturn(historyPointBuild);
        when(historyPointRepository.save(any())).thenReturn(historyPointBuild);
        when(historyPointMapper.toResponse(any())).thenReturn(responseHistoryPointDtoBuild);

        ResponseHistoryPointDto responseHistoryPointDto = historyPointService.create(requestHistoryPointDtoBuild);

        Assertions.assertThat(responseHistoryPointDto).isNotNull();

        verify(historyPointMapper).toEntity(any());
        verify(historyPointRepository).save(any());
        verify(historyPointMapper).toResponse(any());
    }

    @Test
    public void historyPointServiceImplUpdate() {
        when(historyPointRepository.findById(any())).thenReturn(Optional.ofNullable(historyPointBuild));
        when(historyPointMapper.toEntity(any())).thenReturn(historyPointBuild);
        when(historyPointRepository.save(any())).thenReturn(historyPointBuild);
        when(historyPointMapper.toResponse(any())).thenReturn(responseHistoryPointDtoBuild);

        ResponseHistoryPointDto responseHistoryPointDto = historyPointService.update(requestHistoryPointDtoBuild, 1L);

        Assertions.assertThat(responseHistoryPointDto).isNotNull();

        verify(historyPointRepository).save(any());
        verify(historyPointMapper).toEntity(any());
        verify(historyPointRepository).save(any());
        verify(historyPointMapper).toResponse(any());
    }

    @Test
    public void historyPointServiceImplDelete() {
        when(historyPointRepository.findById(any())).thenReturn(Optional.ofNullable(historyPointBuild));
        doNothing().when(historyPointRepository).delete(historyPointBuild);

        assertAll(() -> historyPointService.delete(any()));

        verify(historyPointRepository).findById(any());
        verify(historyPointRepository,times(1)).delete(any());
    }

    @Test
    public void historyPointServiceImplFindById() {
        when(historyPointRepository.findById(any())).thenReturn(Optional.ofNullable(historyPointBuild));
        when(historyPointMapper.toResponse(any())).thenReturn(responseHistoryPointDtoBuild);

        ResponseHistoryPointDto responseHistoryPointDto = historyPointService.findEntity(any()).get();

        Assertions.assertThat(responseHistoryPointDto).isNotNull();

        verify(historyPointRepository).findById(any());
        verify(historyPointMapper).toResponse(any());

    }

    @Test
    public void historyPointServiceImplFindAll() {
        Page<HistoryPoint> historyPoints = Mockito.mock();

        when(historyPointRepository.findAll(any(Pageable.class))).thenReturn(historyPoints);

        List<ResponseHistoryPointDto> all = historyPointService.findAll(1, 10);

        Assertions.assertThat(all).isNotNull();
    }
}