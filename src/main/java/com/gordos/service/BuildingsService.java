package com.gordos.service;

import com.gordos.dto.*;
import com.gordos.entity.BuildingEntity;
import com.gordos.mapper.BuildingMapper;
import com.gordos.repository.BuildingsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingsService {
    private static final String SUCCESSFUL_DELETION = "Building deletion was successful.";
    private static final String SUCCESSFUL_FAILURE = "Building deletion failed.";

    @Autowired
    BuildingsRepository buildingsRepository;

    public BuildingsService(BuildingsRepository buildingsRepository) {
        this.buildingsRepository = buildingsRepository;
    }

    public BuildingsInResponseDTO getBuildingsByCity(String city) {
        List<BuildingEntity> buildingEntityList = buildingsRepository.getBuildingsByCity(city);
        List<BuildingDTO> buildingDTOList = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntityList) {
            BuildingDTO buildingDTO = BuildingMapper.toDTO(buildingEntity);
            buildingDTOList.add(buildingDTO);
        }
        return BuildingsInResponseDTO.builder().buildings(buildingDTOList).build();
    }

    public void addBuilding(BuildingInRequestDTO building) {
        BuildingEntity buildingEntity = BuildingEntity.builder()
               .architect(building.getArchitect())
               .builtDate(building.getBuiltDate())
               .city(building.getCity())
               .image(building.getImage())
               .isProtected(building.getIsProtected())
               .lat(building.getLat())
               .location(building.getLocation())
               .longitude(building.getLongitude())
               .name(building.getName())
               .period(building.getPeriod())
               .state(building.getState())
               .style(building.getStyle())
               .type(building.getType())
               .build();
       buildingsRepository.addBuilding(buildingEntity);
    }
    public BuildingDTO updateBuildingByUUID(String uuid, BuildingInRequestDTO buildingInRequestDTO) {
        BuildingEntity buildingEntity = BuildingEntity.builder()
                .architect(buildingInRequestDTO.getArchitect())
                .builtDate(buildingInRequestDTO.getBuiltDate())
                .city(buildingInRequestDTO.getCity())
                .image(buildingInRequestDTO.getImage())
                .isProtected(buildingInRequestDTO.getIsProtected())
                .lat(buildingInRequestDTO.getLat())
                .location(buildingInRequestDTO.getLocation())
                .longitude(buildingInRequestDTO.getLongitude())
                .name(buildingInRequestDTO.getName())
                .period(buildingInRequestDTO.getPeriod())
                .state(buildingInRequestDTO.getState())
                .style(buildingInRequestDTO.getStyle())
                .type(buildingInRequestDTO.getType())
                .uuid(uuid)
                .build();
        return Objects.isNull(buildingsRepository.updateBuildingByUUID(uuid, buildingEntity))
                ? null
                : BuildingMapper.toDTO(buildingEntity);
    }

    public ResponseDTO deleteBuildingByUuid(String uuid) {
        return ResponseDTO.builder()
                .response(Objects.nonNull(buildingsRepository.deleteBuildingByUuid(uuid))
                        ? SUCCESSFUL_DELETION
                        : SUCCESSFUL_FAILURE)
                .build();
    }

    public BuildingsInResponseDTO getAllBuildings() {
        List<BuildingEntity> buildingEntityList = buildingsRepository.getAllBuildings();
        List<BuildingDTO> buildingDTOList = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntityList) {
            BuildingDTO buildingDTO = BuildingMapper.toDTO(buildingEntity);
            buildingDTOList.add(buildingDTO);
        }
        return BuildingsInResponseDTO.builder().buildings(buildingDTOList).build();
    }
}
