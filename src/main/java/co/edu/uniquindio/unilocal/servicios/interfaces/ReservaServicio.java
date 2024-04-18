package co.edu.uniquindio.unilocal.servicios.interfaces;

import co.edu.uniquindio.unilocal.dto.CrearReservaDTO;
import co.edu.uniquindio.unilocal.dto.ModificarReservaDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.enums.EstadoReserva;
import co.edu.uniquindio.unilocal.modelo.Reserva;

import java.util.List;

public interface ReservaServicio {

    ResultadoDTO crearReserva(CrearReservaDTO crearReservaDTO);

    ResultadoDTO modificarReserva(ModificarReservaDTO modificarReservaDTO);

    ResultadoDTO cancelarReserva(String idReserva) throws Exception;

    List<Reserva> consultarReservasPorEstadoUsuario(String idUsuario, EstadoReserva estadoReserva);

    List<Reserva> consultarReservaPorLugarUsuario(String idUsuario, String idLugar);
}
