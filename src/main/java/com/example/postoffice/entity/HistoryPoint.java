package com.example.postoffice.entity;

import com.example.postoffice.entity.enums.PointType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transaction_history_point")

public class HistoryPoint extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "point_type", nullable = false)
    private PointType pointType;

    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @PositiveOrZero
    @Column(name = "index_department")

    private Integer indexDepartment;

    public HistoryPoint(PointType pointType, LocalDateTime appointmentDate, Integer indexDepartment) {
        this.pointType = pointType;
        this.appointmentDate = appointmentDate;
        this.indexDepartment = indexDepartment;
    }

    public HistoryPoint() {

    }
}