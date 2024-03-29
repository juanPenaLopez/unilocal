package co.edu.uniquindio.unilocal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultadoDTO implements Serializable {

    /**
     * Atributo de seralizacion
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo que determina si esl resultado fue exitoso
     */
    private boolean exitoso;

    /**
     * Atributo que determina el mensaje del error
     */
    private List<MensajeErrorDTO> listaMensajesError = new ArrayList<>();

    /**
     * Atributo que determina la lista de mensajes de informacion
     */
    private List<String> listaMensajesInfo = new ArrayList<>();

    /**
     * Atributo que determina el mensaje de información
     */
    private String mensaje;

}
