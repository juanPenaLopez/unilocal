package co.edu.uniquindio.unilocal.modelo;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario implements Serializable {

    private String id;

    private String mensaje;

    private LocalDateTime fecha;

    private Short calificacion;

    private String codigoCliente;

    private String codigoLugar;

    private String respuesta;

    private LocalDateTime fechaRespuesta;

    private String idClienteRespuesta;
}
