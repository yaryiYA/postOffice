package com.example.postoffice.dto.department;

import com.example.postoffice.dto.AbstractRequestDto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;


/**
 * DTO for {@link com.example.postoffice.entity.Department}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RequestDepartmentDto extends AbstractRequestDto implements Serializable {
    @NotBlank
    private String name;
    @NotBlank
    private String departmentAddress;
    @Min(6)
    @Max(7)
    private Integer index;



}