package com.gordos.repository.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.gordos.entity.BuildingEntity;
import com.gordos.repository.BuildingsRepository;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class BuildingsRepositoryImpl implements BuildingsRepository {
    private static final String COLLECTION_NAME = "buildings";

    @Autowired
    private final Firestore firestore;

    public BuildingsRepositoryImpl(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public List<BuildingEntity> getBuildingsByCity(String city) {
        List<BuildingEntity> buildingsList = new ArrayList<>();

        CollectionReference buildings = firestore.collection(COLLECTION_NAME);

        Query query = buildings.whereEqualTo("city", city).whereEqualTo("enabled", Boolean.TRUE);

        try {
            QuerySnapshot snapshot = query.get().get();
            List<QueryDocumentSnapshot> documents = snapshot.getDocuments();
            for (DocumentSnapshot document : documents) {
                BuildingEntity buildingEntity = BuildingEntity.builder()
                        .architect((String) document.get("architect"))
                        .builtDate((String) document.get("builtDate"))
                        .city((String) document.get("city"))
                        .image((String) document.get("image"))
                        .isProtected((String) document.get("protected"))
                        .lat((String) document.get("lat"))
                        .location((String) document.get("location"))
                        .longitude((String) document.get("long"))
                        .name((String) document.get("name"))
                        .period((String) document.get("period"))
                        .state((String) document.get("state"))
                        .style((String) document.get("style"))
                        .type((String) document.get("type"))
                        .uuid(document.getId())
                        .build();
                buildingsList.add(buildingEntity);
            }
        } catch (Exception e) {
            return null;
        }

        return buildingsList;
    }

    @Override
    public void addBuilding(BuildingEntity building) {
        Map<String, Object> data = new HashMap<>();
        data.put("architect", building.getArchitect());
        data.put("builtDate", building.getBuiltDate());
        data.put("city", building.getCity());
        data.put("enabled", Boolean.TRUE);
        data.put("image", building.getImage());
        data.put("protected", building.getIsProtected());
        data.put("lat", building.getLat());
        data.put("location", building.getLocation());
        data.put("long", building.getLongitude());
        data.put("name", building.getName());
        data.put("period", building.getPeriod());
        data.put("state", building.getState());
        data.put("style", building.getStyle());
        data.put("type", building.getType());

        firestore.collection(COLLECTION_NAME).add(data);
    }

    @Override
    public BuildingEntity updateBuildingByUUID(String uuid, BuildingEntity building) {
        DocumentReference buildingRef = firestore.collection(COLLECTION_NAME).document(uuid);
        try {
            ApiFuture<DocumentSnapshot> future = buildingRef.get();
            DocumentSnapshot doc = future.get();
            if (buildingExists(uuid, building.getCity())) {
                // El documento existe, devuelve la entidad correspondiente
                BuildingEntity entity = doc.toObject(BuildingEntity.class);
                return entity;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Boolean buildingExists(String uuid, String city) {
        List<BuildingEntity> buildingEntityList = this.getBuildingsByCity(city);
        for (BuildingEntity entity : buildingEntityList) {
            if (uuid.equals(entity.getUuid())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public String deleteBuildingByUuid(String uuid) {
        try {
            DocumentReference document = firestore.collection(COLLECTION_NAME).document(uuid);
            ApiFuture<WriteResult> future = document.update("enabled", Boolean.FALSE);
            future.get();
            return uuid;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<BuildingEntity> getAllBuildings() {
        List<BuildingEntity> buildingsList = new ArrayList<>();

        CollectionReference buildings = firestore.collection(COLLECTION_NAME);

        Query query = buildings.whereEqualTo("enabled", Boolean.TRUE);

        try {
            QuerySnapshot snapshot = query.get().get();
            List<QueryDocumentSnapshot> documents = snapshot.getDocuments();
            for (DocumentSnapshot document : documents) {
                BuildingEntity buildingEntity = BuildingEntity.builder()
                        .architect((String) document.get("architect"))
                        .builtDate((String) document.get("builtDate"))
                        .city((String) document.get("city"))
                        .image((String) document.get("image"))
                        .isProtected((String) document.get("protected"))
                        .lat((String) document.get("lat"))
                        .location((String) document.get("location"))
                        .longitude((String) document.get("long"))
                        .name((String) document.get("name"))
                        .period((String) document.get("period"))
                        .state((String) document.get("state"))
                        .style((String) document.get("style"))
                        .type((String) document.get("type"))
                        .uuid(document.getId())
                        .build();
                buildingsList.add(buildingEntity);
            }
        } catch (Exception e) {
            return null;
        }

        return buildingsList;
    }
}
