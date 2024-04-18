package co.edu.uniquindio.unilocal.servicios.interfaces;

import co.edu.uniquindio.unilocal.dto.ActualizarEventoDTO;
import co.edu.uniquindio.unilocal.dto.EventoDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;

public interface EventoServicio {

    ResultadoDTO crearEvento(EventoDTO eventoDTO);

    ResultadoDTO modificarEvento(ActualizarEventoDTO actualizarEventoDTO) throws Exception;

    ResultadoDTO eliminarEvento(String idEvento) throws Exception;

    void listarEventosVigentesPorNegocio(String idNegocio);
}
