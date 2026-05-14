package com.daniel.edupulse_api.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "schools")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String inepCode;

    @Column(nullable = false)
    private String name;

    private String city;

    private String state = "PB";

    // Campo para o pontuaçao
    private boolean hasInternet;
    private boolean hasLibrary;
    private boolean hasComputerLab;
    private boolean hasSportsCourt;
    private boolean hasScienceLab;
    private boolean hasAccessibility;
    private boolean hasStudentWifi;

    @Column(columnDefinition = "TEXT")
    private String technicalNotes;

    private Integer studentCount;

    @Enumerated(EnumType.STRING)
    private SchoolLevel level;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean active = true;
}
