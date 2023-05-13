package com.gordos.repository.impl;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.gordos.config.GoogleCloudConfiguration;
import com.gordos.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Repository
public class ImageRepositoryImpl implements ImageRepository {
    private static final String BUCKET = "gordos-proyecto-mapa.appspot.com";
    private static final String EMPTY = "";
    private static final String URL = "https://storage.googleapis.com/gordos-proyecto-mapa.appspot.com/";

    @Autowired
    private Storage storage;

    @Autowired
    private final GoogleCloudConfiguration googleCloudConfiguration;

    public ImageRepositoryImpl(Storage storage, GoogleCloudConfiguration googleCloudConfiguration) {
        this.storage = storage;
        this.googleCloudConfiguration = googleCloudConfiguration;
    }

    @Override
    public String uploadImage(MultipartFile image) {
        // Crea una referencia al archivo en Firebase Storage
        String fileName = image.getOriginalFilename();
        BlobId blobId = BlobId.of(BUCKET, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        // Sube la imagen a Firebase Storage
        try {
            storage.create(blobInfo, image.getBytes());
            return URL + fileName;
        } catch (IOException e) {
            return EMPTY;
        }
    }
}
