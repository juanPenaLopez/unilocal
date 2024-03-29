package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.modelo.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CuentaRepo extends MongoRepository<Cuenta, String> {
}
