package com.daniel.edupulse_api.domain.repository;

import com.daniel.edupulse_api.domain.model.School;
import com.daniel.edupulse_api.domain.model.SchoolLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SchoolRepository extends JpaRepository<School, UUID> {
    Page<School> findAllByActiveTrue(Pageable pageable);

    Page<School> findByCityIgnoreCaseAndActiveTrue(String city, Pageable pageable);

    List<School> findByCityIgnoreCaseAndActiveTrue(String city);

    List<School> findByCityIgnoreCaseAndLevelAndActiveTrue(String city, SchoolLevel level);

    //void deleteByInepCode(String inepCode);

    Optional<School> findByInepCodeAndActiveTrue(String inepCode);

    Page<School> findByNameContainingIgnoreCaseAndActiveTrue(String name, Pageable pageable);

    List<School> findByLevel(SchoolLevel level);
}
