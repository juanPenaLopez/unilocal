package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.modelo.Lugar;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LugarRepo extends MongoRepository<Lugar, String> {

    Lugar findByNombre(String nombreNegocio);
}
