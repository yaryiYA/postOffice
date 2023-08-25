package com.example.postoffice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department extends AbstractEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "department_address", nullable = false)
    private String departmentAddress;

    @Column(name = "index", nullable = false, unique = true,length = 6)
    private Integer index;


}