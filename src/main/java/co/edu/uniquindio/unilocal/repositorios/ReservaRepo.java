package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.enums.EstadoReserva;
import co.edu.uniquindio.unilocal.modelo.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservaRepo extends MongoRepository<Reserva, String> {

    List<Reserva> findAllByIdUsuarioAndEstadoReserva(String idUsuario, EstadoReserva estadoReserva);

    List<Reserva> findAllByIdUsuarioAndIdLugar(String idUsuario, String idLugar);
}
