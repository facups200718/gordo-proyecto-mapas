package com.gordos.controller;

import com.gordos.dto.ImageInResponseDTO;
import com.gordos.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Controller
@RequestMapping("/api/v1")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/images/upload")
    public ResponseEntity<ImageInResponseDTO> uploadImage(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(this.imageService.uploadImage(image));
    }
}
