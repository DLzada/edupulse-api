package com.daniel.edupulse_api.dto;

public record SchoolDTO(
        String inepCode,
        String name,
        String city,
        String state,
        Integer studentCount,
        boolean hasInternet,
        boolean hasLibrary,
        boolean hasComputerLab,
        boolean hasSportsCourt,
        Double infrastructureScore
) {}
