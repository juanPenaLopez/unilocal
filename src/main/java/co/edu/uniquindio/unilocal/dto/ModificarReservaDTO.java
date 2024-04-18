package co.edu.uniquindio.unilocal.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ModificarReservaDTO (

        String idReserva,

        LocalDate fechaReserva,

        LocalTime horaReserva,

        Integer cantidadPersonas
){
}
