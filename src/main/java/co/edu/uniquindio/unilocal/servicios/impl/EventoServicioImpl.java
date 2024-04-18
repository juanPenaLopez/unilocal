package co.edu.uniquindio.unilocal.servicios.impl;

import co.edu.uniquindio.unilocal.dto.ActualizarEventoDTO;
import co.edu.uniquindio.unilocal.dto.EventoDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.enums.Estado;
import co.edu.uniquindio.unilocal.modelo.Evento;
import co.edu.uniquindio.unilocal.repositorios.EventoRepo;
import co.edu.uniquindio.unilocal.servicios.interfaces.EventoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoServicioImpl implements EventoServicio {

    private final EventoRepo eventoRepo;

    @Override
    public ResultadoDTO crearEvento(EventoDTO eventoDTO) {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        Evento evento = new Evento();
        evento.setNombre(eventoDTO.nombre());
        evento.setDescripcion(eventoDTO.descripcion());
        evento.setFechaInicio(eventoDTO.fechaInicio());
        evento.setFechaFin(eventoDTO.fechaFin());
        evento.setIdLugar(eventoDTO.idLugar());
        evento.setEstadoEvento(Estado.ACTIVO);

        eventoRepo.save(evento);

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("El evento se creó correctamente");

        return resultadoDTO;
    }

    @Override
    public ResultadoDTO modificarEvento(ActualizarEventoDTO actualizarEventoDTO) throws Exception {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        Optional<Evento> eventoOptional = eventoRepo.findById(actualizarEventoDTO.id());

        if(eventoOptional.isEmpty()){
            throw new Exception("No se encuentra el evento con id: " + actualizarEventoDTO.id());
        }

        Evento evento = eventoOptional.get();

        if (actualizarEventoDTO.fechaInicio().isAfter(evento.getFechaInicio())) {
            throw new Exception("La nueva fecha de inicio no puede ser posterior a la fecha de inicio original.");
        }

        if (actualizarEventoDTO.fechaInicio().isAfter(evento.getFechaFin()) || actualizarEventoDTO.fechaFin().isAfter(evento.getFechaFin())) {
            throw new Exception("Las nuevas fechas de inicio y/o fin no pueden ser posteriores a la fecha fin originales.");
        }
        
        evento.setNombre(actualizarEventoDTO.nombre());
        evento.setDescripcion(actualizarEventoDTO.descripcion());
        evento.setFechaInicio(actualizarEventoDTO.fechaInicio());
        evento.setFechaFin(actualizarEventoDTO.fechaFin());

        eventoRepo.save(evento);

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("Se actualizó de manera correcta el evento");

        return resultadoDTO;
    }

    @Override
    public ResultadoDTO eliminarEvento(String idEvento) throws Exception {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        Optional<Evento> eventoOptional = eventoRepo.findById(idEvento);

        if(eventoOptional.isEmpty()){
            throw new Exception("No existe el evento con id: " + idEvento);
        }

        eventoOptional.get().setEstadoEvento(Estado.INACTIVO);

        eventoRepo.save(eventoOptional.get());

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("Se eliminó correctamente el evento");

        return resultadoDTO;
    }

    @Override
    public List<Evento> listarEventosVigentesPorNegocio(String idNegocio) {
        return eventoRepo.findAllByIdLugarAndEstadoEvento(idNegocio, Estado.ACTIVO);
    }
}
