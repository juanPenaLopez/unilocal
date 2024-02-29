package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.modelo.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservaRepo extends MongoRepository<Reserva, String> {
}
