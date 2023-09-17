package com.example.postoffice.controller.rest.impl.v1.parcelController;

import com.example.postoffice.controller.rest.CommonController;
import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
import com.example.postoffice.dto.parsel.RequestParcelDto;
import com.example.postoffice.dto.parsel.ResponseParcelDto;
import com.example.postoffice.entity.Parcel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public interface ParcelController extends CommonController<Parcel,
        RequestParcelDto,
        ResponseParcelDto> {

    @PostMapping("/registration")
    ResponseParcelDto registrationParcel(@RequestBody @Valid RequestParcelDto requestParcelDto,
                                         @RequestParam(value = "index")
                                         @PositiveOrZero Integer index);

    @GetMapping("/arrive")
    ResponseParcelDto arriveAtDepartment(@RequestParam(value = "identifierParcel")
                                         @PositiveOrZero Long identifierParcel,
                                         @RequestParam(value = "index") Integer indexDepartment);

    @GetMapping("/leave")
    ResponseParcelDto leaveAtDepartment(@RequestParam(value = "identifierParcel")
                                        @PositiveOrZero Long identifierParcel);

    @GetMapping("/delivery")
    ResponseParcelDto deliveryToRecipient(@RequestParam(value = "identifierParcel")
                                          @PositiveOrZero Long identifierParcel);

    @GetMapping("/history")
    List<ResponseHistoryPointDto> getHistoryParcel(@RequestParam(value = "identifierParcel")
                                                   @PositiveOrZero Long identifierParcel);
}
