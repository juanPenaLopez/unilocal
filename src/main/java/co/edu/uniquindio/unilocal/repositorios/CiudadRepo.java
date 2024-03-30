package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.modelo.Ciudad;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CiudadRepo extends MongoRepository<Ciudad, String> {
}
