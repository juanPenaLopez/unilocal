package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.modelo.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepo extends MongoRepository<Usuario, String> {
}
