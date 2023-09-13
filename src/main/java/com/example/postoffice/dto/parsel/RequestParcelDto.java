package com.example.postoffice.dto.parsel;

import com.example.postoffice.dto.AbstractRequestDto;
import com.example.postoffice.dto.historyPoint.RequestHistoryPointDto;
import com.example.postoffice.entity.HistoryPoint;
import com.example.postoffice.entity.enums.ParcelType;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link com.example.postoffice.entity.Parcel}
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestParcelDto extends AbstractRequestDto implements Serializable {
    private Long identifier;
    @NotNull
    private ParcelType parcelType;
    @Min(111111)
    @Max(999999)
    private Integer recipientIndex;
    @NotBlank
    private String recipientAddress;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private List<RequestHistoryPointDto> historyPoints = new ArrayList<>();
}