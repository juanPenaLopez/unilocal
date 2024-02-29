package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.modelo.Moderador;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModeradorRepo extends MongoRepository<Moderador, String> {
}
