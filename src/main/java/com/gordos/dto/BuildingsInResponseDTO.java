package com.gordos.dto;

import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class BuildingsInResponseDTO {
    List<BuildingDTO> addresses;
}
