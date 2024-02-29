package co.edu.uniquindio.unilocal.modelo;

import co.edu.uniquindio.unilocal.enums.CategoriaLugar;
import co.edu.uniquindio.unilocal.enums.Estado;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Document("lugares")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lugar implements Serializable {

    @Id
    private String id;

    private String nombre;

    private String descripcion;

    private CategoriaLugar categoriaLugar;

    private List<Imagen> imagenes;

    private List<Comentario> comentarios;

    private Estado estado;

    private Ubicacion ubicacion;

    private LocalDateTime fechaCreacion;

    private String usuarioCreacion;

    private List<Horario> horarios;

    private List<String> telefonos;

    private List<Revision> revisiones;
}
