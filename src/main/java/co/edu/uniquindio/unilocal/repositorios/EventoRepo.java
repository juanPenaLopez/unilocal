package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.enums.Estado;
import co.edu.uniquindio.unilocal.modelo.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventoRepo extends MongoRepository<Evento, String> {
    List<Evento> findAllByIdLugarAndEstadoEvento(String idNegocio, Estado estado);
}
