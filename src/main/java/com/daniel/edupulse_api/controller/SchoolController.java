package com.daniel.edupulse_api.controller;

import com.daniel.edupulse_api.domain.model.SchoolLevel;
import com.daniel.edupulse_api.dto.CityStatsDTO;
import com.daniel.edupulse_api.dto.SchoolComparisonDTO;
import com.daniel.edupulse_api.dto.SchoolDTO;
import com.daniel.edupulse_api.service.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<SchoolDTO> getAll(
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC)Pageable pageable){
        return service.findAll(pageable);
    }

    @GetMapping("/ranking/{city}")
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolDTO> getRanking(@PathVariable String city, @RequestParam(required = false) SchoolLevel level){
        return service.getRankingByCity(city, level);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDTO create(@Valid @RequestBody SchoolDTO dto){
        return service.save(dto);
    }

    @DeleteMapping("/{inepCode}")
    public ResponseEntity<Void> delete(@PathVariable String inepCode){
        service.delete(inepCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats/{city}")
    @ResponseStatus(HttpStatus.OK)
    public CityStatsDTO getStatsCity(@PathVariable String city){
        return service.getCityStats(city);
    }

    @GetMapping("/critical/{city}")
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolDTO> getCriticalSchools(@PathVariable String city){
        return service.getCriticalSchools(city);
    }

    @PutMapping("{inepCode}")
    @ResponseStatus(HttpStatus.OK)
    public SchoolDTO update(@PathVariable String inepCode, @Valid @RequestBody SchoolDTO dto){
        return service.update(inepCode, dto);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<SchoolDTO> searchByName(@RequestParam String name, @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC)Pageable pageable){
        return service.findByName(name, pageable);
    }

    @GetMapping("/level/{level}")
    @ResponseStatus(HttpStatus.OK)
    public Page<SchoolDTO> getByLevel(@PathVariable SchoolLevel level, @PageableDefault(page = 0, size = 5, sort = "name", direction = Sort.Direction.ASC)Pageable pageable){
        return service.findByLevel(level, pageable);
    }

    @GetMapping("/deficit")
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolDTO> findSchoolWithDeficit(@RequestParam String city, @RequestParam String resource){
        return service.findSchoolWithDeficit(city, resource);
    }

    @GetMapping("/compare")
    @ResponseStatus(HttpStatus.OK)
    public SchoolComparisonDTO compare(@RequestParam String inep1, @RequestParam String inep2){
        return service.compareSchools(inep1, inep2);
    }
}
