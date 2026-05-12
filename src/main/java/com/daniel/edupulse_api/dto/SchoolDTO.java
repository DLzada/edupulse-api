package com.daniel.edupulse_api.dto;

public record SchoolDTO(
        String inepCode,
        String name,
        String city,
        String state,
        boolean hasInternet,
        Integer studentCount
) {}
