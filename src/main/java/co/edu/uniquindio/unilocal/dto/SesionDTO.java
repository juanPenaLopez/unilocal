package co.edu.uniquindio.unilocal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SesionDTO (
        @NotBlank @Email String email,
        @NotBlank  String password
){
}
