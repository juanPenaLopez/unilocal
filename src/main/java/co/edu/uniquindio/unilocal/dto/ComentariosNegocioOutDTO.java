package co.edu.uniquindio.unilocal.dto;

import co.edu.uniquindio.unilocal.modelo.Comentario;

import java.util.ArrayList;
import java.util.List;

public record ComentariosNegocioOutDTO (
        List<Comentario> comentarioList
){
}
