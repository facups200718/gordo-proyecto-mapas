package com.gordos.entity;

import lombok.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class BuildingEntity {
    private String uuid;
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

    public Map<String, Object> getAllFields() {
        Map<String, Object> fieldMap = new HashMap<>();

        // Obtener todos los campos declarados en la clase actual y sus superclases
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Acceder a campos privados si los hubiera
            String fieldName = field.getName();
            try {
                Object fieldValue = field.get(this); // Obtener el valor del campo en el objeto actual
                fieldMap.put(fieldName, fieldValue);
            } catch (IllegalAccessException e) {
                // Manejar la excepción si no se puede acceder al campo
                e.printStackTrace();
            }
        }

        return fieldMap;
    }

}
