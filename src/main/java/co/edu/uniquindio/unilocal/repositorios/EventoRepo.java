package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.modelo.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventoRepo extends MongoRepository<Evento, String> {
}
