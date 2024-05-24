package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.enums.Estado;
import co.edu.uniquindio.unilocal.modelo.Comentario;
import co.edu.uniquindio.unilocal.modelo.Lugar;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LugarRepo extends MongoRepository<Lugar, String> {

    Lugar findByNombre(String nombreNegocio);

    @Query("{ '_id': ?0, 'comentarios.id': ?1 }")
    Optional<Lugar> findLugarByIdAndComentarioId(String lugarId, String comentarioId);

    @Query(value = "{ '_id': ?0 }", fields = "{ 'comentarios' : 1, '_id' : 0 }")
    List<Comentario> findComentariosByLugarId(String lugarId);

    @Aggregation(pipeline = {
            "{ $match: { '_id': ?0 } }",
            "{ $unwind: '$comentarios' }",
            "{ $group: { _id: null, promedioCalificacion: { $avg: '$comentarios.calificacion' } } }"
    })
    Double calcularPromedioCalificaciones(String lugarId);

    List<Lugar> findAllByEstado(Estado estado);

    List<Lugar> findAllByUsuarioCreacion(String idUsuario);
}
