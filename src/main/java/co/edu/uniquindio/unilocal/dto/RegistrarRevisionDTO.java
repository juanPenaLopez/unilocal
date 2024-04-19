package co.edu.uniquindio.unilocal.dto;

import co.edu.uniquindio.unilocal.enums.EstadoRevision;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegistrarRevisionDTO (

        @NotBlank @NotNull
        String idModerador,

        @NotBlank
        EstadoRevision estadoRevision,

        @NotBlank @NotNull
        LocalDateTime fechaRevision,

        String descripcion,

        @NotBlank @NotNull
        String idLugar
){
}
