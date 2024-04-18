package co.edu.uniquindio.unilocal.dto;

import co.edu.uniquindio.unilocal.enums.CategoriaLugar;
import co.edu.uniquindio.unilocal.modelo.Horario;
import co.edu.uniquindio.unilocal.modelo.Imagen;
import co.edu.uniquindio.unilocal.modelo.Ubicacion;

import java.util.List;

public record CrearLugarDTO (
        String nombre,

        CategoriaLugar categoriaLugar,

        List<String> telefonos,

        String descripcion,

        Ubicacion ubicacion,

        List<Horario> horarioList,

        List<Imagen> urlFotos,

        EventoDTO eventoDTO
){
}
