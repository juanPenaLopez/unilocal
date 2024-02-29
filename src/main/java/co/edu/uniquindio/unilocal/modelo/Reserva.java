package co.edu.uniquindio.unilocal.modelo;

import co.edu.uniquindio.unilocal.enums.EstadoReserva;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Document("reservas")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reserva implements Serializable {

    @Id
    private String id;

    private LocalDate fechaReserva;

    private LocalTime horaReserva;

    private Integer numeroPersonas;

    private String idUsuario;

    private EstadoReserva estadoReserva;

    private String idLugar;
}
