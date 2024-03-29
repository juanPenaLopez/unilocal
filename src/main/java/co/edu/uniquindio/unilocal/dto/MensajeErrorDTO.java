package co.edu.uniquindio.unilocal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MensajeErrorDTO implements Serializable {

    /**
     * Atributo de seralizacion
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo que determina el código del error
     */
    private String codigoError;

    /**
     * Atributo que determina el mensaje del error
     */
    private String mensajeError;
}
