package com.gordos.repository;

import com.gordos.dto.BuildingDTO;

import java.util.List;

public interface BuildingsRepository {
    List<BuildingDTO> getAllBuildings(String city);

    void addBuilding(BuildingDTO building);

    BuildingDTO updateBuildingByUUID(String uuid, BuildingDTO buildingDTO);

    void deleteBuildingByUuid(String uuid);
}
