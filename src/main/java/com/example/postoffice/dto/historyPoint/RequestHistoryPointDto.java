package com.example.postoffice.dto.historyPoint;

import com.example.postoffice.dto.AbstractRequestDto;
import com.example.postoffice.entity.enums.PointType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.postoffice.entity.HistoryPoint}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RequestHistoryPointDto extends AbstractRequestDto implements Serializable {
    @NotNull
    private PointType pointType;
    @PastOrPresent
    private LocalDateTime appointmentDate;
    @PositiveOrZero
    private Integer indexDepartment;
}