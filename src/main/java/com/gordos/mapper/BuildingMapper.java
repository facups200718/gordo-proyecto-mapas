package com.gordos.mapper;

import com.gordos.dto.BuildingDTO;
import com.gordos.entity.BuildingEntity;

public class BuildingMapper {
    public static BuildingDTO toDTO(BuildingEntity entity) {
        BuildingDTO dto = new BuildingDTO();
        dto.setUuid(entity.getUuid());
        dto.setImage(entity.getImage());
        dto.setPeriod(entity.getPeriod());
        dto.setCity(entity.getCity());
        dto.setArchitect(entity.getArchitect());
        dto.setType(entity.getType());
        dto.setLongitude(entity.getLongitude());
        dto.setBuiltDate(entity.getBuiltDate());
        dto.setIsProtected(entity.getIsProtected());
        dto.setName(entity.getName());
        dto.setLocation(entity.getLocation());
        dto.setStyle(entity.getStyle());
        dto.setState(entity.getState());
        dto.setLat(entity.getLat());
        return dto;
    }
}