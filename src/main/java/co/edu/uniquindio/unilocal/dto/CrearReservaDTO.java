package co.edu.uniquindio.unilocal.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record CrearReservaDTO (

        String idLugar,

        String idUsuario,

        LocalDate fechaReserva,

        LocalTime horaReserva,

        Integer cantidadPersonas
){
}
