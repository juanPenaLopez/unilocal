package co.edu.uniquindio.unilocal.modelo;

import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Horario implements Serializable {

    private String id;

    private String dia;

    private LocalTime horaInicio;

    private LocalTime horaFin;
}
