package com.daniel.edupulse_api.dto;

public record CityStatsDTO(
        String name,
        long totalSchools,
        long totalStudents,
        double averageInfrastructureScore,
        long schoolWithStudentWifi,
        double wifiCoveragePercentage
) {}
