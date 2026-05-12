package com.daniel.edupulse_api.controller;

import com.daniel.edupulse_api.dto.SchoolDTO;
import com.daniel.edupulse_api.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolDTO> getAll(){
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDTO create(@RequestBody SchoolDTO dto){
        return service.save(dto);
    }
}
