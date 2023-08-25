package com.example.postoffice.controller.rest.impl.v1;

import com.example.postoffice.controller.rest.BaseController;
import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
import com.example.postoffice.dto.parsel.RequestParcelDto;
import com.example.postoffice.dto.parsel.ResponseParcelDto;
import com.example.postoffice.entity.Parcel;
import com.example.postoffice.service.impl.ParcelServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/parcel")
public class ParcelControllerImpl extends BaseController<Parcel,
        RequestParcelDto,
        ResponseParcelDto,
        ParcelServiceImpl> {


    public ParcelControllerImpl(ParcelServiceImpl service) {
        super(service);
    }



    @PostMapping("/registration/{index}")
    ResponseEntity<ResponseParcelDto> registrationParcel(@RequestBody @Valid RequestParcelDto parcel,
                                                         @PathVariable("index") @PositiveOrZero Integer index) {

        return ResponseEntity.ok(service.registrationParcel(parcel, index));
    }

    @GetMapping("/{identifier}/arrive/{index}")
    ResponseEntity<ResponseParcelDto> arriveAtDepartment(@PathVariable("identifier") @PositiveOrZero  Long identifierParcel,
                                                         @PathVariable("index") Integer indexDepartment) {
        return ResponseEntity.ok(service.arriveAtDepartment(identifierParcel, indexDepartment));
    }

    @GetMapping("/{identifier}/leave")
    ResponseEntity<ResponseParcelDto> arriveAtDepartment(@PathVariable("identifier") @PositiveOrZero Long identifier) {
        return ResponseEntity.ok(service.leaveDepartment(identifier));
    }

    @GetMapping("/{identifier}/delivery")
    ResponseEntity<ResponseParcelDto> deliveryToRecipient(@PathVariable("identifier") @PositiveOrZero Long identifier) {
        return ResponseEntity.ok(service.deliveryToRecipient(identifier));
    }

    @GetMapping("/{identifier}/history")
    ResponseEntity<List<ResponseHistoryPointDto>> getHistoryParcel(@PathVariable("identifier") @PositiveOrZero Long identifier) {
        return ResponseEntity.ok(service.getHistoryParcel(identifier));
    }


}
