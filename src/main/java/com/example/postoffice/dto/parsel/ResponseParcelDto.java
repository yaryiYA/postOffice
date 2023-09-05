package com.example.postoffice.dto.parsel;

import com.example.postoffice.dto.AbstractResponseDto;
import com.example.postoffice.entity.HistoryPoint;
import com.example.postoffice.entity.enums.ParcelType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
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
    private Long identifier;
    @NotNull
    private ParcelType parcelType;
    @PositiveOrZero
    private Integer recipientIndex;
    @NotBlank
    private String recipientAddress;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private List<HistoryPoint> historyPoints;
}