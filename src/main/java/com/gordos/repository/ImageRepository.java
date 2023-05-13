package com.gordos.repository;

import org.springframework.web.multipart.MultipartFile;

public interface ImageRepository {
    String uploadImage(MultipartFile image);
}
