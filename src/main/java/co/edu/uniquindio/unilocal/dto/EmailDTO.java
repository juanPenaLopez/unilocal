package co.edu.uniquindio.unilocal.dto;

import jakarta.validation.constraints.NotBlank;

public record EmailDTO (

        String asunto,

        @NotBlank
        String cuerpo,

        @NotBlank
        String destinatario
){
}
