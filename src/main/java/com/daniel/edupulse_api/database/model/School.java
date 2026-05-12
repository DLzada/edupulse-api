package com.daniel.edupulse_api.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "schools")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String inepCode;

    @Column(nullable = false)
    private String nome;

    private String city;

    private String state;

    // Campo para o pontuaçao
    private boolean hasInternet;
    private boolean hasLibrary;
    private boolean hasComputerLab;
    private boolean hasSportsCourt;

    private Integer studentCount;
}
