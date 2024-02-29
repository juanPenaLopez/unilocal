package co.edu.uniquindio.unilocal.modelo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ubicacion implements Serializable {

    private Double latitud;

    private Double longitud;
}
