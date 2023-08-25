package com.example.postoffice.entity;

import com.example.postoffice.entity.enums.ParcelType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "parcel")
public class Parcel extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
        private Long identifier;

    @Enumerated(EnumType.STRING)
    @Column(name = "parcel_type")
    private ParcelType parcelType;



    @Column(name = "recipient_index", nullable = false)
    private Integer recipientIndex;

    @Column(name = "recipient_address", nullable = false)
    private String recipientAddress;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<HistoryPoint> historyPoints = new ArrayList<>();

}