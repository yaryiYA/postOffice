package com.example.postoffice.dto.historyPoint;

import com.example.postoffice.dto.AbstractRequestDto;
import com.example.postoffice.entity.enums.PointType;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.postoffice.entity.HistoryPoint}
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestHistoryPointDto extends AbstractRequestDto implements Serializable {
    @NotNull
    private PointType pointType;
    @Min(100000)
    @Max(999999)
    private Integer indexDepartment;
}