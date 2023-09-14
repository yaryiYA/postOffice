package com.example.postoffice.dto.parsel;

import com.example.postoffice.dto.AbstractResponseDto;
import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
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
public class ResponseParcelDto extends AbstractResponseDto implements Serializable {
    private Long id;
    @NotNull
    private ParcelType parcelType;
    @Min(100000)
    @Max(999999)
    private Integer recipientIndex;
    @NotBlank
    private String recipientAddress;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private List<ResponseHistoryPointDto> historyPoints = new ArrayList<>();
}