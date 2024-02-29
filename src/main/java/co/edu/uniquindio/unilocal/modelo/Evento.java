package co.edu.uniquindio.unilocal.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("eventos")
public class Evento implements Serializable {

    @Id
    private String id;

    private String nombre;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private String descripcion;

    private String idLugar;
}
