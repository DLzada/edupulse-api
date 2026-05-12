package com.daniel.edupulse_api.domain.repository;

import com.daniel.edupulse_api.domain.model.School;
import com.daniel.edupulse_api.domain.model.SchoolLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SchoolRepository extends JpaRepository<School, UUID> {
    List<School> findByCityIgnoreCase(String city);

    List<School> findByCityIgnoreCaseAndLevel(String city, SchoolLevel level);
}
