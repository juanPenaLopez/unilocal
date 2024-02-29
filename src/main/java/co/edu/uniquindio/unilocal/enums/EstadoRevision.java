package co.edu.uniquindio.unilocal.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EstadoRevision {

    APROBADO(0),
    RECHAZADO(1),
    PENDIENTE(2);

    private Integer index;

    public Integer getValor() {
        return index;
    }
}
