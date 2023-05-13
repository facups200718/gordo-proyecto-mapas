package com.gordos.repository;

import com.gordos.entity.BuildingEntity;

import java.util.List;

public interface BuildingsRepository {
    List<BuildingEntity> getBuildingsByCity(String city);

    void addBuilding(BuildingEntity building);

    BuildingEntity updateBuildingByUUID(String uuid, BuildingEntity buildingDTO);

    String deleteBuildingByUuid(String uuid);

    List<BuildingEntity> getAllBuildings();
}
