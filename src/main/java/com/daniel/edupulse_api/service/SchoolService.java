package com.daniel.edupulse_api.service;

import com.daniel.edupulse_api.domain.model.School;
import com.daniel.edupulse_api.domain.model.SchoolLevel;
import com.daniel.edupulse_api.domain.repository.SchoolRepository;
import com.daniel.edupulse_api.dto.CityStatsDTO;
import com.daniel.edupulse_api.dto.SchoolDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<SchoolDTO> findAll(){
        return schoolRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    private SchoolDTO mapToDTO(School school) {
        double connectivityScore = 0;
        double pedagogicScore = 0;
        double wellnessScore = 0;

        // 1. Conectividade(max 30)
        if(school.isHasInternet()) connectivityScore += 10;
        if (school.isHasStudentWifi()) connectivityScore += 20;

        //2. estrutura pedagogica(max 40)
        if(school.isHasLibrary()) pedagogicScore += 15;
        if(school.isHasComputerLab()) pedagogicScore += 15;
        if(school.isHasScienceLab()) pedagogicScore += 10;

        //3. bem-estar (max 30)
        if(school.isHasAccessibility()) wellnessScore += 15;
        if(school.isHasSportsCourt()) wellnessScore += 15;

        double finalScore = connectivityScore + pedagogicScore + wellnessScore;

        String status = "Crítica";
        if(finalScore > 70) status = "Referência";
        else if(finalScore > 40) status = "Básica";

        return new SchoolDTO(
                school.getInepCode(),
                school.getName(),
                school.getCity(),
                school.getState(),
                school.getStudentCount(),
                school.getLevel(),
                school.isHasInternet(),
                school.isHasLibrary(),
                school.isHasComputerLab(),
                school.isHasSportsCourt(),
                school.isHasScienceLab(),
                school.isHasAccessibility(),
                school.isHasStudentWifi(),
                school.getTechnicalNotes(),
                finalScore,
                status
        );
    }

    @Transactional
    public SchoolDTO save(SchoolDTO dto){
        School school = School.builder()
                .inepCode(dto.inepCode())
                .name(dto.name())
                .city(dto.city())
                .state(dto.state())
                .studentCount(dto.studentCount())
                .level(dto.level())
                .hasInternet(dto.hasInternet())
                .hasLibrary(dto.hasLibrary())
                .hasComputerLab(dto.hasComputerLab())
                .hasSportsCourt(dto.hasSportsCourt())
                .hasScienceLab(dto.hasScienceLab())
                .hasAccessibility(dto.hasAccessibility())
                .hasStudentWifi(dto.hasStudentWifi())
                .technicalNotes(dto.technicalNotes())
                .build();

        School savedSchool = schoolRepository.save(school);

        return mapToDTO(savedSchool);
    }

    public List<SchoolDTO> getRankingByCity(String city, SchoolLevel level){
        List<School> schools;

        if(level != null){
            schools = schoolRepository.findByCityIgnoreCaseAndLevel(city, level);
        } else {
            schools = schoolRepository.findByCityIgnoreCase(city);
        }
        return schools.stream()
                .map(this::mapToDTO)
                .sorted(Comparator.comparing(SchoolDTO::infrastructureScore).reversed())
                .toList();
    }

    @Transactional
    public void delete(String inepCode){
        schoolRepository.deleteByInepCode(inepCode);
    }

    public CityStatsDTO getCityStats(String city){
        List<School> schools = schoolRepository.findByCityIgnoreCase(city);

        if(schools.isEmpty()){
            throw new NoSuchElementException("Cidade não encontrada ou sem escolas cadastradas.");
        }

        long totalSchools = schools.size();
        long totalStudents = schools.stream().mapToLong(School::getStudentCount).sum();

        double avgScore = schools.stream()
                .mapToDouble(s -> mapToDTO(s).infrastructureScore())
                .average()
                .orElse(0.0);

        long wifiSchools = schools.stream().filter(School::isHasInternet).count();
        double wifiPercentage = (double) wifiSchools / totalSchools * 100;

        return new CityStatsDTO(
                city,
                totalSchools,
                totalStudents,
                avgScore,
                wifiSchools,
                wifiPercentage
        );
    }

    public List<SchoolDTO> getCriticalSchools(String city){
        List<School> schools = schoolRepository.findByCityIgnoreCase(city);

        return schools.stream()
                .map(this::mapToDTO)
                .filter(dto -> dto.infrastructureScore() < 50.0)
                .sorted(Comparator.comparing(SchoolDTO::infrastructureScore))
                .toList();
    }

    @Transactional
    public SchoolDTO update(String inepCode, SchoolDTO dto){
        School school = schoolRepository.findByInepCode(inepCode)
                .orElseThrow(()-> new NoSuchElementException("Escola não encontrada!"));

        school.setName(dto.name());
        school.setStudentCount(dto.studentCount());
        school.setLevel(dto.level());
        school.setHasInternet(dto.hasInternet());
        school.setHasLibrary(dto.hasLibrary());
        school.setHasComputerLab(dto.hasComputerLab());
        school.setHasSportsCourt(dto.hasSportsCourt());
        school.setHasScienceLab(dto.hasScienceLab());
        school.setHasAccessibility(dto.hasAccessibility());
        school.setHasStudentWifi(dto.hasStudentWifi());
        school.setTechnicalNotes(dto.technicalNotes());

        School updateSchool = schoolRepository.save(school);

        return mapToDTO(updateSchool);
    }
}
