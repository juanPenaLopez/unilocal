package co.edu.uniquindio.unilocal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CrearComentarioDTO (

        String id,

        @NotBlank
        String mensaje,

        @NotBlank
        LocalDateTime fecha,

        @NotBlank
        Short calificacion,

        @NotNull @NotBlank
        String codigoCliente,

        @NotNull @NotBlank
        String codigoLugar

){
}
