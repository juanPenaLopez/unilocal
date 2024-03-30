package co.edu.uniquindio.unilocal.modelo;

import co.edu.uniquindio.unilocal.enums.Estado;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@ToString
public class Usuario extends Cuenta implements Serializable {

    private String nombreCompleto;

    private String urlFoto;

    private String nickname;

    private Estado estado;

    private String idCiudad;
}
