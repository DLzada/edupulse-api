package com.daniel.edupulse_api.dto;

import com.daniel.edupulse_api.domain.model.SchoolLevel;
import jakarta.validation.constraints.*;

public record SchoolDTO(
        @NotBlank(message = "O código do INEP é obrigatório!")
        String inepCode,

        @NotBlank(message = "O Nome da escola é obrigatório!")
        String name,

        @NotBlank(message = "A cidade é obrigatória!")
        String city,

        @Size(min = 2, max = 2, message = "Use a sigla do estado (Ex: PB)")
        String state,

        @PositiveOrZero(message = "A quantidade de alunos nao pode ser negativa!")
        Integer studentCount,

        @NotNull(message = "O nivel da escola deve ser informado")
        SchoolLevel level,

        boolean hasInternet,
        boolean hasLibrary,
        boolean hasComputerLab,
        boolean hasSportsCourt,
        boolean hasScienceLab,
        boolean hasAccessibility,
        boolean hasStudentWifi,

        String technicalNotes,

        Double infrastructureScore,
        String infrastructureStatus
) {}
