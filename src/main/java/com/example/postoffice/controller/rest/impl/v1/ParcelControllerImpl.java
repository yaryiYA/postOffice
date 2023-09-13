package com.example.postoffice.controller.rest.impl.v1;

import com.example.postoffice.controller.rest.BaseController;
import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
import com.example.postoffice.dto.parsel.RequestParcelDto;
import com.example.postoffice.dto.parsel.ResponseParcelDto;
import com.example.postoffice.entity.Parcel;
import com.example.postoffice.mapper.historyPoint.HistoryPointMapperImpl;
import com.example.postoffice.mapper.parcel.ParcelMapperImpl;
import com.example.postoffice.service.impl.ParcelServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import static com.example.postoffice.entity.enums.PointType.DELIVERED;

@Validated
@RestController
@RequestMapping("/api/v1/parcel")
public class ParcelControllerImpl extends BaseController<Parcel,
        RequestParcelDto,
        ResponseParcelDto,
        ParcelServiceImpl,
        ParcelMapperImpl> {

    private final HistoryPointMapperImpl historyPointMapper;

    @Autowired
    protected ParcelControllerImpl(ParcelServiceImpl service, ParcelMapperImpl mapper,
                                   HistoryPointMapperImpl historyPointMapper) {
        super(service, mapper);
        this.historyPointMapper = historyPointMapper;
    }

    @PostMapping("/registration")
    ResponseEntity<ResponseParcelDto> registrationParcel(@RequestBody @Valid RequestParcelDto requestParcelDto,
                                                         @RequestParam(value = "index") @PositiveOrZero Integer index) {
        Parcel parcel = mapper.toEntity(requestParcelDto);

        return ResponseEntity.ok(mapper.toResponse(service.registrationParcel(parcel, index)));
    }

    @GetMapping("/arrive")
    ResponseEntity<?> arriveAtDepartment(@RequestParam(value = "identifierParcel") @PositiveOrZero Long identifierParcel,
                                         @RequestParam(value = "index") Integer indexDepartment) {
        if (sendBlockingParcel(identifierParcel)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("letter has been delivered");
        }
        ResponseParcelDto response = mapper.toResponse(service.arriveAtDepartment(identifierParcel, indexDepartment));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/leave")
    ResponseEntity<?> leaveAtDepartment(@RequestParam(value = "identifierParcel") @PositiveOrZero Long identifierParcel) {
        if (sendBlockingParcel(identifierParcel)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("letter has been delivered");
        }
        return ResponseEntity.ok(mapper.toResponse(service.leaveDepartment(identifierParcel)));
    }

    @GetMapping("/delivery")
    ResponseEntity<ResponseParcelDto> deliveryToRecipient(@RequestParam(value = "identifierParcel") @PositiveOrZero Long identifierParcel) {
        return ResponseEntity.ok(mapper.toResponse(service.deliveryToRecipient(identifierParcel)));
    }

    @GetMapping("/history")
    ResponseEntity<List<ResponseHistoryPointDto>> getHistoryParcel(@RequestParam(value = "identifierParcel") @PositiveOrZero Long identifierParcel) {
        List<ResponseHistoryPointDto> responseHistoryPointDtos = service.getHistoryParcel(identifierParcel).stream()
                .map(historyPointMapper::toResponse).toList();

        return ResponseEntity.ok(responseHistoryPointDtos);
    }

    private Boolean sendBlockingParcel(Long identifier) {
        return super.service.findEntity(identifier).getHistoryPoints().stream()
                .anyMatch(historyPoint -> historyPoint.getPointType() == DELIVERED);
    }


}
