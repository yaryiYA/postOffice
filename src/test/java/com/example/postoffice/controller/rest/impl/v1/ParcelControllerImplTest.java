package com.example.postoffice.controller.rest.impl.v1;

import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
import com.example.postoffice.dto.parsel.RequestParcelDto;
import com.example.postoffice.dto.parsel.ResponseParcelDto;
import com.example.postoffice.entity.Parcel;
import com.example.postoffice.entity.enums.ParcelType;
import com.example.postoffice.service.impl.ParcelServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(controllers = ParcelControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class ParcelControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ParcelServiceImpl parcelService;
    private RequestParcelDto requestParcelDtoBuild;
    private ResponseParcelDto responseParcelDtoBuild;

    @BeforeEach
    public void setup() {

        requestParcelDtoBuild = RequestParcelDto.builder()
                .identifier(1L)
                .parcelType(ParcelType.LETTER)
                .recipientIndex(123456)
                .recipientAddress("test")
                .firstName("ivan")
                .lastName("ivanov")
                .historyPoints(new ArrayList<>())
                .build();

        responseParcelDtoBuild = ResponseParcelDto.builder()
                .identifier(1L)
                .parcelType(ParcelType.LETTER)
                .recipientIndex(123456)
                .recipientAddress("test")
                .firstName("ivan")
                .lastName("ivanov")
                .historyPoints(new ArrayList<>())
                .build();
    }

    @Test
    public void ParcelControllerCreate() throws Exception {
        when(parcelService.create(any())).thenReturn(responseParcelDtoBuild);

        ResultActions response = mockMvc.perform(post("/api/v1/parcel/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestParcelDtoBuild))
                .characterEncoding("utf-8"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.firstName", CoreMatchers.is(responseParcelDtoBuild.getFirstName())))
                .andExpect(jsonPath("$.lastName", CoreMatchers.is(responseParcelDtoBuild.getLastName())));
    }


    @Test
    public void ParcelControllerFindAll() throws Exception {
        List<ResponseParcelDto> list = new ArrayList<>();
        list.add(responseParcelDtoBuild);
        list.add(responseParcelDtoBuild);

        when(parcelService.findAll(1, 10)).thenReturn(list);

        ResultActions response = mockMvc.perform(get("/api/v1/parcel/all")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNo", "1")
                .param("pageSize", "10")
                .characterEncoding("utf-8"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(parcelService).findAll(1, 10);
    }

    @Test
    public void ParcelControllerFindEntity() throws Exception {
        when(parcelService.findEntity(any())).thenReturn(Optional.ofNullable(responseParcelDtoBuild));

        ResultActions response = mockMvc.perform(get("/api/v1/parcel/{ID}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestParcelDtoBuild))
                .characterEncoding("utf-8"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.firstName", CoreMatchers.is(responseParcelDtoBuild.getFirstName())))
                .andExpect(jsonPath("$.lastName", CoreMatchers.is(responseParcelDtoBuild.getLastName())));

        verify(parcelService).findEntity(any());
    }

    // base контроллер


    @Test
    void registrationParcel() throws Exception {
        when(parcelService.registrationParcel(any(),any())).thenReturn(responseParcelDtoBuild);

        ResultActions response = mockMvc.perform(post("/api/v1/parcel/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .param("index", "123456")
                .content(objectMapper.writeValueAsString(requestParcelDtoBuild))
                .characterEncoding("utf-8"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.firstName", CoreMatchers.is(responseParcelDtoBuild.getFirstName())))
                .andExpect(jsonPath("$.lastName", CoreMatchers.is(responseParcelDtoBuild.getLastName())));

        verify(parcelService).registrationParcel(any(),any());
    }

    @Test
    void arriveAtDepartment() throws Exception {

        when(parcelService.arriveAtDepartment(any(), any())).thenReturn(responseParcelDtoBuild);
        when(parcelService.findEntity(any())).thenReturn(Optional.ofNullable(responseParcelDtoBuild));

        ResultActions response = mockMvc.perform(get("/api/v1/parcel/arrive")
                .param("identifierParcel", "1")
                .param("index", "123456")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestParcelDtoBuild))
                .characterEncoding("utf-8"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.firstName", CoreMatchers.is(responseParcelDtoBuild.getFirstName())))
                .andExpect(jsonPath("$.lastName", CoreMatchers.is(responseParcelDtoBuild.getLastName())));

        verify(parcelService).arriveAtDepartment(any(),any());
        verify(parcelService).findEntity(any());
    }



    @Test
    void testLeaveAtDepartment() throws Exception {
        when(parcelService.leaveDepartment(any())).thenReturn(responseParcelDtoBuild);
        when(parcelService.findEntity(any())).thenReturn(Optional.ofNullable(responseParcelDtoBuild));


        ResultActions response = mockMvc.perform(get("/api/v1/parcel/leave")
                .param("identifierParcel", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestParcelDtoBuild))
                .characterEncoding("utf-8"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.firstName", CoreMatchers.is(responseParcelDtoBuild.getFirstName())))
                .andExpect(jsonPath("$.lastName", CoreMatchers.is(responseParcelDtoBuild.getLastName())));

        verify(parcelService).leaveDepartment(any());
        verify(parcelService).findEntity(any());
    }

    @Test
    void deliveryToRecipient() throws Exception {
        when(parcelService.deliveryToRecipient(any())).thenReturn(responseParcelDtoBuild);
        ResultActions response = mockMvc.perform(get("/api/v1/parcel/delivery")

                .param("identifierParcel", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestParcelDtoBuild))
                .characterEncoding("utf-8"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.firstName", CoreMatchers.is(responseParcelDtoBuild.getFirstName())))
                .andExpect(jsonPath("$.lastName", CoreMatchers.is(responseParcelDtoBuild.getLastName())));

        verify(parcelService).deliveryToRecipient(any());
    }

    @Test
    void getHistoryParcel() throws Exception {
        List<ResponseHistoryPointDto> list = new ArrayList<>();
        list.add(new ResponseHistoryPointDto());
        list.add(new ResponseHistoryPointDto());

        when(parcelService.getHistoryParcel(any())).thenReturn(list);

        ResultActions response = mockMvc.perform(get("/api/v1/parcel/history")
                .contentType(MediaType.APPLICATION_JSON)
                .param("identifierParcel", "1")
                .characterEncoding("utf-8"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(parcelService).getHistoryParcel(any());
    }
}