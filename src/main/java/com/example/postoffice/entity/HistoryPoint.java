package com.example.postoffice.entity;

import com.example.postoffice.entity.enums.PointType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transaction_history_point")

public class HistoryPoint extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "point_type", nullable = false)
    private PointType pointType;

    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @PositiveOrZero
    @Column(name = "index_department")

    private Integer indexDepartment;
}