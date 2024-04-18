package co.edu.uniquindio.unilocal.dto;

import java.time.LocalDateTime;

public record ActualizarEventoDTO (

        String nombre,

        LocalDateTime fechaInicio,

        LocalDateTime fechaFin,

        String descripcion,

        String id
){
}
