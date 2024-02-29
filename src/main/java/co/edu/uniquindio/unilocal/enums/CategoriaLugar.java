package co.edu.uniquindio.unilocal.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CategoriaLugar {

    PANADERIA(0),
    BAR(1),
    CAFETERIA(2),
    RESTAURANTE(3),
    DISCOTECA(4),
    OTRO(5),
    SUPERMERCADO(6),
    TIENDA(7),
    PIQUETEADERO(8);

    private Integer index;

    public Integer getValor() {
        return index;
    }
}
