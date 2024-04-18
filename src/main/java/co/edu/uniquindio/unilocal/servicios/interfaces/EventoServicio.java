package co.edu.uniquindio.unilocal.servicios.interfaces;

import co.edu.uniquindio.unilocal.dto.ActualizarEventoDTO;
import co.edu.uniquindio.unilocal.dto.EventoDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.modelo.Evento;

import java.util.List;

public interface EventoServicio {

    ResultadoDTO crearEvento(EventoDTO eventoDTO);

    ResultadoDTO modificarEvento(ActualizarEventoDTO actualizarEventoDTO) throws Exception;

    ResultadoDTO eliminarEvento(String idEvento) throws Exception;

    List<Evento> listarEventosVigentesPorNegocio(String idNegocio);
}
