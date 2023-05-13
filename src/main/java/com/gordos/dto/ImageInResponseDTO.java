package com.gordos.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ImageInResponseDTO {
    private String imageUrl;
}
