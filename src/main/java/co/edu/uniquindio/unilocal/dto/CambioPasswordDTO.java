package co.edu.uniquindio.unilocal.dto;

import jakarta.validation.constraints.NotBlank;

public record CambioPasswordDTO (
        @NotBlank
        String passwordNueva,
        @NotBlank
        String id,
        String token
){
}
