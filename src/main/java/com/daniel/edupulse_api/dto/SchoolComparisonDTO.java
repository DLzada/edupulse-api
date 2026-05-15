package com.daniel.edupulse_api.dto;

import java.util.List;

public record SchoolComparisonDTO(
   SchoolDTO school1,
   SchoolDTO school2,
   String winner,
   List<String> school1Advantages,
   List<String> school2Advantages
) {}
