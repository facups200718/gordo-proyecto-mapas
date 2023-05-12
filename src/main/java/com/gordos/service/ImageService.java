package com.gordos.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.gordos.config.GoogleCloudConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    private Storage storage;

    @Autowired
    private final GoogleCloudConfiguration googleCloudConfiguration;

    public ImageService(Storage storage, GoogleCloudConfiguration googleCloudConfiguration) {
        this.storage = storage;
        this.googleCloudConfiguration = googleCloudConfiguration;
    }

    public String uploadImage(MultipartFile image) {
        // Crea una referencia al archivo en Firebase Storage
        String fileName = image.getOriginalFilename();
        BlobId blobId = BlobId.of("gordos-proyecto-mapa.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        // Sube la imagen a Firebase Storage
        try {
            storage.create(blobInfo, image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error al subir la imagen a Firebase Storage", e);
        }

        // Devuelve la URL de descarga de la imagen
        return "https://storage.googleapis.com/gordos-proyecto-mapa.appspot.com/" + fileName;
    }
}
