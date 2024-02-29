package co.edu.uniquindio.unilocal.modelo;

import co.edu.uniquindio.unilocal.enums.EstadoRevision;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Revision implements Serializable {

    private String id;

    private String descripcion;

    private EstadoRevision estadoRevision;

    private LocalDateTime fecha;

    private String idModerador;
}
