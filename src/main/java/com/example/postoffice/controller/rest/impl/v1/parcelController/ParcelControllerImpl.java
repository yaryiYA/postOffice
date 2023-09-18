package com.example.postoffice.controller.rest.impl.v1.parcelController;

import com.example.postoffice.controller.rest.BaseController;
import com.example.postoffice.dto.historypoint.ResponseHistoryPointDto;
import com.example.postoffice.dto.parsel.RequestParcelDto;
import com.example.postoffice.dto.parsel.ResponseParcelDto;
import com.example.postoffice.entity.Parcel;
import com.example.postoffice.exception.DeliveryException;
import com.example.postoffice.mapper.historyPoint.HistoryPointMapperImpl;
import com.example.postoffice.mapper.parcel.ParcelMapperImpl;
import com.example.postoffice.service.impl.parcelService.ParcelService;

import org.springframework.beans.factory.annotation.Autowired;
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
        ParcelService,
        ParcelMapperImpl> {

    private final HistoryPointMapperImpl historyPointMapper;

    @Autowired
    protected ParcelControllerImpl(ParcelService service, ParcelMapperImpl mapper,
                                   HistoryPointMapperImpl historyPointMapper) {
        super(service, mapper);
        this.historyPointMapper = historyPointMapper;
    }

    @PostMapping("/registration")
    public ResponseParcelDto registrationParcel(@RequestBody @Valid RequestParcelDto requestParcelDto,
                                                @RequestParam(value = "index") @PositiveOrZero Integer index) {
        Parcel parcel = mapper.toEntity(requestParcelDto);
        return mapper.toResponse(service.registrationParcel(parcel, index));
    }

    @GetMapping("/arrive")
    public ResponseParcelDto arriveAtDepartment(@RequestParam(value = "identifierParcel") @PositiveOrZero Long identifierParcel,
                                                @RequestParam(value = "index") Integer indexDepartment) {
        if (sendBlockingParcel(identifierParcel)) {
            throw new DeliveryException("letter has been delivered");
        }

        return mapper.toResponse(service.arriveAtDepartment(identifierParcel, indexDepartment));
    }

    @GetMapping("/leave")
    public ResponseParcelDto leaveAtDepartment(@RequestParam(value = "identifierParcel") @PositiveOrZero Long identifierParcel) {
        if (sendBlockingParcel(identifierParcel)) {
            throw new DeliveryException("letter has been delivered");
        }
        return mapper.toResponse(service.leaveDepartment(identifierParcel));
    }

    @GetMapping("/delivery")
    public ResponseParcelDto deliveryToRecipient(@RequestParam(value = "identifierParcel") @PositiveOrZero Long identifierParcel) {
        return mapper.toResponse(service.deliveryToRecipient(identifierParcel));
    }

    @GetMapping("/history")
    public List<ResponseHistoryPointDto> getHistoryParcel(@RequestParam(value = "identifierParcel") @PositiveOrZero Long identifierParcel) {

        return service.getHistoryParcel(identifierParcel).stream()
                .map(historyPointMapper::toResponse).toList();
    }

    private Boolean sendBlockingParcel(Long identifier) {
        return super.service.findEntity(identifier).getHistoryPoints().stream()
                .anyMatch(historyPoint -> historyPoint.getPointType() == DELIVERED);
    }
}
