package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.modelo.Moderador;
import co.edu.uniquindio.unilocal.modelo.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModeradorRepo extends MongoRepository<Moderador, String> {

    Moderador findByEmail(String email);
}
