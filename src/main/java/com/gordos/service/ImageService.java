package com.gordos.service;

import com.gordos.dto.ImageInResponseDTO;
import com.gordos.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageInResponseDTO uploadImage(MultipartFile image) {
        return ImageInResponseDTO.builder()
                .imageUrl(imageRepository.uploadImage(image))
                .build();
    }
}
