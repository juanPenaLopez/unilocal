package co.edu.uniquindio.unilocal.servicios.impl;

import co.edu.uniquindio.unilocal.dto.CrearReservaDTO;
import co.edu.uniquindio.unilocal.dto.ModificarReservaDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.enums.EstadoReserva;
import co.edu.uniquindio.unilocal.modelo.Reserva;
import co.edu.uniquindio.unilocal.repositorios.ReservaRepo;
import co.edu.uniquindio.unilocal.servicios.interfaces.ReservaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservaServicioImpl implements ReservaServicio {

    private final ReservaRepo reservaRepo;

    @Override
    public ResultadoDTO crearReserva(CrearReservaDTO crearReservaDTO) {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        Reserva reserva = new Reserva();
        reserva.setIdUsuario(crearReservaDTO.idUsuario());
        reserva.setIdLugar(crearReservaDTO.idLugar());
        reserva.setFechaReserva(crearReservaDTO.fechaReserva());
        reserva.setHoraReserva(crearReservaDTO.horaReserva());
        reserva.setNumeroPersonas(crearReservaDTO.cantidadPersonas());
        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);

        reservaRepo.save(reserva);

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("Se creó correctamente la reserva");

        return resultadoDTO;
    }

    @Override
    public ResultadoDTO modificarReserva(ModificarReservaDTO modificarReservaDTO) {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        Optional<Reserva> reservaOptional = reservaRepo.findById(modificarReservaDTO.idReserva());

        if(reservaOptional.isEmpty()){
            resultadoDTO.setExitoso(false);
            resultadoDTO.setMensaje("No existe la reserva con id: " + modificarReservaDTO.idReserva());
            return resultadoDTO;
        }

        Reserva reserva = reservaOptional.get();

        if (reserva.getFechaReserva().equals(modificarReservaDTO.fechaReserva())) {
            LocalTime horaActualReserva = reserva.getHoraReserva();
            LocalTime nuevaHoraReserva = modificarReservaDTO.horaReserva();

            // Calcula la diferencia en minutos entre las dos horas
            long diferenciaMinutos = Math.abs(ChronoUnit.MINUTES.between(horaActualReserva, nuevaHoraReserva));

            if (diferenciaMinutos < 30) {
                resultadoDTO.setExitoso(false);
                resultadoDTO.setMensaje("La nueva hora de reserva debe ser al menos 30 minutos diferente a la hora reservada existente.");
                return resultadoDTO;
            }
        }

        reserva.setHoraReserva(modificarReservaDTO.horaReserva());
        reserva.setFechaReserva(modificarReservaDTO.fechaReserva());
        reserva.setNumeroPersonas(modificarReservaDTO.cantidadPersonas());

        reservaRepo.save(reserva);

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("Se modificó correctamente la reserva");

        return resultadoDTO;
    }

    @Override
    public ResultadoDTO cancelarReserva(String idReserva) throws Exception {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        Optional<Reserva> reservaOptional = reservaRepo.findById(idReserva);

        if(reservaOptional.isEmpty()){
            throw new Exception("No existe la reserva con id: " + idReserva);
        }

        reservaOptional.get().setEstadoReserva(EstadoReserva.CANCELADA);

        reservaRepo.save(reservaOptional.get());

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("La reserva fue cancelada con +éxito");

        return resultadoDTO;
    }

    @Override
    public List<Reserva> consultarReservasPorEstadoUsuario(String idUsuario, EstadoReserva estadoReserva) {
        return reservaRepo.findAllByIdUsuarioAndEstadoReserva(idUsuario, estadoReserva);
    }

    @Override
    public List<Reserva> consultarReservaPorLugarUsuario(String idUsuario, String idLugar) {
        return reservaRepo.findAllByIdUsuarioAndIdLugar(idUsuario, idLugar);
    }

    @Override
    public List<Reserva> consultarReservaPorUsuario(String idUsuario) {
        return reservaRepo.findAllByIdUsuario(idUsuario);
    }
}
