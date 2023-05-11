package com.gordos.service;

import com.gordos.dto.BuildingInRequestDTO;
import com.gordos.dto.BuildingsInResponseDTO;
import com.gordos.dto.BuildingDTO;
import com.gordos.repository.BuildingsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingsService {
    @Autowired
    BuildingsRepository buildingsRepository;

    public BuildingsService(BuildingsRepository buildingsRepository) {
        this.buildingsRepository = buildingsRepository;
    }

    public BuildingsInResponseDTO getAddresses(String city) {
        List<BuildingDTO> buildingDTOList = buildingsRepository.getAllAddresses(city);
        return BuildingsInResponseDTO.builder().addresses(buildingDTOList).build();
    }

    public void addBuilding(BuildingInRequestDTO building) {
       BuildingDTO buildingDTO = BuildingDTO.builder()
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
       buildingsRepository.addBuilding(buildingDTO);
    }
    public BuildingDTO updateBuildingByUUID(String uuid, BuildingInRequestDTO buildingInRequestDTO) {
        BuildingDTO buildingDTO = BuildingDTO.builder()
                .architect(buildingInRequestDTO.getArchitect())
                .builtDate(buildingInRequestDTO.getBuiltDate())
                .city(buildingInRequestDTO.getCity())
                .enabled(buildingInRequestDTO.getEnabled())
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

        return buildingsRepository.updateBuildingByUUID(uuid, buildingDTO);
    }

    public void deleteBuildingByUuid(String uuid) {
        buildingsRepository.deleteBuildingByUuid(uuid);
    }
}
