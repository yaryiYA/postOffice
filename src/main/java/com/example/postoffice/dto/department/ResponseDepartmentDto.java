package com.example.postoffice.dto.department;

import com.example.postoffice.dto.AbstractResponseDto;
import com.example.postoffice.dto.historyPoint.ResponseHistoryPointDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.postoffice.entity.Department}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseDepartmentDto extends AbstractResponseDto implements Serializable {
    @NotBlank
    private String name;
    @NotBlank
    private String departmentAddress;
    @Min(6)
    @Max(7)
    private Integer index;
}