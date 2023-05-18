package com.gordos.service;

import com.gordos.dto.ImageInResponseDTO;
import com.gordos.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageInResponseDTO uploadImage(MultipartFile image) throws IOException {
        MultipartFile renamedImage = renameFile(image);
        return ImageInResponseDTO.builder()
                .imageUrl(imageRepository.uploadImage(renamedImage))
                .build();
    }

    private static MultipartFile renameFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String extension = getExtension(originalFilename);
        String hash = generateHash(originalFilename);
        String currentDate = getCurrentDateTime();
        String newFilename = hash + "_" + currentDate + extension;

        InputStream inputStream = file.getInputStream();
        byte[] content = inputStream.readAllBytes();

        return new MockMultipartFile(newFilename, newFilename, file.getContentType(), content);
    }

    private static String getExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex);
        }
        return "";
    }

    private static String generateHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return currentDateTime.format(formatter);
    }
}
