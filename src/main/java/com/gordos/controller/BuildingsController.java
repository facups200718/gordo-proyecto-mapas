package com.gordos.controller;

import com.gordos.dto.BuildingInRequestDTO;
import com.gordos.dto.BuildingDTO;
import com.gordos.service.BuildingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/api/v1")
public class BuildingsController {
    @Autowired
    private BuildingsService buildingsService;

    @GetMapping("/{city}/buildings")
    @ResponseBody
    public ResponseEntity<?> getBuildingsByCity(@PathVariable String city) {
        try {
            return ResponseEntity.ok(buildingsService.getBuildings(city));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/building")
    public ResponseEntity<?> addBuilding(@RequestBody BuildingInRequestDTO building) {
        try {
            buildingsService.addBuilding(building);
            return ResponseEntity.ok("Building added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/buildings/{uuid}")
    @ResponseBody
    public ResponseEntity<BuildingDTO> updateBuildingByUUID(@PathVariable String uuid, @RequestBody BuildingInRequestDTO buildingDTO) {
            BuildingDTO updatedBuildingDTO = buildingsService.updateBuildingByUUID(uuid, buildingDTO);
        if (updatedBuildingDTO != null) {
            return ResponseEntity.ok(updatedBuildingDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/buildings/{uuid}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable String uuid) {
        buildingsService.deleteBuildingByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
