package com.daniel.edupulse_api.database.repository;

import com.daniel.edupulse_api.database.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SchoolRepository extends JpaRepository<School, UUID> {
}
