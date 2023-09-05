package com.example.postoffice.controller.rest.impl.v1;

import com.example.postoffice.controller.rest.BaseController;
import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
import com.example.postoffice.dto.parsel.RequestParcelDto;
import com.example.postoffice.dto.parsel.ResponseParcelDto;
import com.example.postoffice.entity.Parcel;
import com.example.postoffice.service.impl.ParcelServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.postoffice.entity.enums.PointType.DELIVERED;

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

    @PostMapping("/registration")
    ResponseEntity<ResponseParcelDto> registrationParcel(@RequestBody @Valid RequestParcelDto parcel,
                                                         @RequestParam(value = "index") @PositiveOrZero Integer index) {

        return ResponseEntity.ok(service.registrationParcel(parcel, index));
    }

    @GetMapping("/arrive")
    ResponseEntity<?> arriveAtDepartment(@RequestParam(value = "identifierParcel") @PositiveOrZero Long identifierParcel,
                                         @RequestParam(value = "index") Integer indexDepartment) {
        if (sendBlockingParcel(identifierParcel)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("letter has been delivered");
        }
        return ResponseEntity.ok(service.arriveAtDepartment(identifierParcel, indexDepartment));
    }

    @GetMapping("/leave")
    ResponseEntity<?> arriveAtDepartment(@RequestParam(value = "identifierParcel") @PositiveOrZero Long identifierParcel) {
        if (sendBlockingParcel(identifierParcel)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("letter has been delivered");
        }
        return ResponseEntity.ok(service.leaveDepartment(identifierParcel));
    }

    @GetMapping("/delivery")
    ResponseEntity<ResponseParcelDto> deliveryToRecipient(@RequestParam(value = "identifierParcel") @PositiveOrZero Long identifierParcel) {
        return ResponseEntity.ok(service.deliveryToRecipient(identifierParcel));
    }

    @GetMapping("/history")
    ResponseEntity<List<ResponseHistoryPointDto>> getHistoryParcel(@RequestParam(value = "identifierParcel") @PositiveOrZero Long identifierParcel) {
        return ResponseEntity.ok(service.getHistoryParcel(identifierParcel));
    }

    private Boolean sendBlockingParcel(Long identifier) {
        return super.service.findEntity(identifier).orElseThrow().getHistoryPoints().stream()
                .anyMatch(historyPoint -> historyPoint.getPointType() == DELIVERED);
    }


}
