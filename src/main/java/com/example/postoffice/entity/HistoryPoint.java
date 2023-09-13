package com.example.postoffice.entity;

import com.example.postoffice.entity.enums.PointType;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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