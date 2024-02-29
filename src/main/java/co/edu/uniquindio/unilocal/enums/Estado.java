package co.edu.uniquindio.unilocal.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Estado {

    ACTIVO(0),
    INACTIVO(1);

    private Integer index;

    public Integer getValor() {
        return index;
    }
}
