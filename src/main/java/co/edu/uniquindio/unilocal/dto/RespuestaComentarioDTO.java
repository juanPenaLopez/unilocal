package co.edu.uniquindio.unilocal.dto;

import java.time.LocalDateTime;

public record RespuestaComentarioDTO (

        String idLugar,

        String respuesta,

        LocalDateTime fechaRespuesta,

        String idUsuarioRespuesta,

        String idComentario

){
}
