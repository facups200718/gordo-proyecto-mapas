package com.gordos.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class BuildingsByCityInRequestDTO {
    private String city;
}
