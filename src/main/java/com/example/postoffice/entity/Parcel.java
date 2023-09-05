package com.example.postoffice.entity;

import com.example.postoffice.entity.enums.ParcelType;
import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "parcel")
public class Parcel extends AbstractEntity {

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