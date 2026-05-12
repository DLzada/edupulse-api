package com.daniel.edupulse_api.domain.model;

public enum SchoolLevel {
    FUNDAMENTAL_1("Ensino Fundamental I"),
    FUNDAMENTAL_2("Ensino Fundamental II"),
    ENSINO_MEDIO("Ensino Médio"),
    TECNICO("Ensino Técnico"),
    SUPERIOR("Ensino Superior");

    private final String description;

    SchoolLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
