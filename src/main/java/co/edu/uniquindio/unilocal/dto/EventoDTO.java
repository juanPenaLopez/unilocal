package co.edu.uniquindio.unilocal.dto;

import co.edu.uniquindio.unilocal.enums.Estado;

import java.time.LocalDateTime;

public record EventoDTO (
        String nombre,

        LocalDateTime fechaInicio,

        LocalDateTime fechaFin,

        String descripcion,

        String idLugar

){
}
