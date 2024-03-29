package co.edu.uniquindio.unilocal.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO (

        @NotBlank
        String correo,

        @NotBlank
        String contrasena
){
}
