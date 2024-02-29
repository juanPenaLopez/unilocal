package co.edu.uniquindio.unilocal.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EstadoReserva {

    PENDIENTE(0),
    FINALIZADA(1),
    CANCELADA(2),
    RECHAZADA(3),
    CONFIRMADA(4);

    private Integer index;

    public Integer getValor() {
        return index;
    }
}
