package com.gordos.controller;

import com.gordos.dto.*;
import com.gordos.service.BuildingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin
@Controller
@RequestMapping("/api/v1")
public class BuildingsController {
    @Autowired
    private BuildingsService buildingsService;

    @PostMapping("/buildings")
    @ResponseBody
    public ResponseEntity<BuildingsInResponseDTO> getBuildingsByCity(@RequestBody BuildingsByCityInRequestDTO buildingsByCityInRequestDTO) {
        return ResponseEntity.ok(buildingsService.getBuildingsByCity(buildingsByCityInRequestDTO.getCity()));
    }

    @PostMapping("/building")
    public ResponseEntity<ResponseDTO> addBuilding(@RequestBody BuildingInRequestDTO building) {
        try {
            buildingsService.addBuilding(building);
            return ResponseEntity.ok(ResponseDTO.builder().response("Building added successfully.").build());
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDTO.builder().response("Building addition failed.").build());
        }
    }

    @PutMapping("/buildings/{uuid}")
    @ResponseBody
    public ResponseEntity<?> updateBuildingByUUID(@PathVariable String uuid, @RequestBody BuildingInRequestDTO buildingDTO) {
        BuildingDTO buildingInResponseDTO = buildingsService.updateBuildingByUUID(uuid, buildingDTO);
        return Objects.nonNull(buildingInResponseDTO)
                ? ResponseEntity.ok(buildingInResponseDTO)
                : ResponseEntity.ok(ResponseDTO.builder().response("Building update failed.").build());
    }

    @DeleteMapping("/buildings/{uuid}")
    public ResponseEntity<ResponseDTO> deleteBuilding(@PathVariable String uuid) {
        return ResponseEntity.ok(buildingsService.deleteBuildingByUuid(uuid));
    }

    @GetMapping("/buildings")
    @ResponseBody
    public ResponseEntity<BuildingsInResponseDTO> getAllBuildings() {
        return ResponseEntity.ok(buildingsService.getAllBuildings());
    }
}
