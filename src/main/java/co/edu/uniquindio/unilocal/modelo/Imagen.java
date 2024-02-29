package co.edu.uniquindio.unilocal.modelo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Imagen implements Serializable {

    private String id;

    private String url;

    private String descripcion;
}
