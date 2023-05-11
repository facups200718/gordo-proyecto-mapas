package com.gordos.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class BuildingInRequestDTO {
    private String image;
    private String period;
    private String city;
    private String architect;
    private String type;
    private String longitude;
    private String builtDate;
    private String isProtected;
    private String name;
    private String location;
    private String style;
    private String state;
    private String lat;
    private Boolean enabled;
}
