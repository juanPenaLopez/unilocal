package co.edu.uniquindio.unilocal.servicios.interfaces;

import co.edu.uniquindio.unilocal.dto.ComentariosNegocioOutDTO;
import co.edu.uniquindio.unilocal.dto.CrearComentarioDTO;
import co.edu.uniquindio.unilocal.dto.RespuestaComentarioDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;

public interface ComentarioServicio {

    ResultadoDTO crearComentario(CrearComentarioDTO crearComentarioDTO) throws Exception;

    ResultadoDTO responderComentario(RespuestaComentarioDTO respuestaComentarioDTO) throws Exception;

    ComentariosNegocioOutDTO listarComentariosNegocio(String idLugar);

    Double calcularPromedioCalificacionesPorNegocio(String idLugar);
}
