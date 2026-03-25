package org.example.veterinariadto.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MascotaDTO {

    private Long id;
    private String nombre;
    private int edad;
}