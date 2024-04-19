package co.edu.uniquindio.unilocal.servicios.impl;

import co.edu.uniquindio.unilocal.dto.ComentariosNegocioOutDTO;
import co.edu.uniquindio.unilocal.dto.CrearComentarioDTO;
import co.edu.uniquindio.unilocal.dto.RespuestaComentarioDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.modelo.Comentario;
import co.edu.uniquindio.unilocal.modelo.Lugar;
import co.edu.uniquindio.unilocal.repositorios.LugarRepo;
import co.edu.uniquindio.unilocal.servicios.interfaces.ComentarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final LugarRepo lugarRepo;

    @Override
    public ResultadoDTO crearComentario(CrearComentarioDTO crearComentarioDTO) throws Exception {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        if(crearComentarioDTO.codigoLugar() == null){
            throw new Exception("El id del lugar no puede venir nulo");
        }

        Optional<Lugar> lugarOptional = lugarRepo.findById(crearComentarioDTO.codigoLugar());

        Comentario comentario = new Comentario();
        comentario.setId(UUID.randomUUID().toString());
        comentario.setMensaje(crearComentarioDTO.mensaje());
        comentario.setFecha(crearComentarioDTO.fecha());
        comentario.setCodigoLugar(crearComentarioDTO.codigoLugar());
        comentario.setCodigoCliente(crearComentarioDTO.codigoCliente());
        comentario.setCalificacion(crearComentarioDTO.calificacion());

        if(lugarOptional.get().getComentarios() == null){
            List<Comentario> comentarios = new ArrayList<>();
            comentarios.add(comentario);
            lugarOptional.get().setComentarios(comentarios);
        }else {
            lugarOptional.get().getComentarios().add(comentario);
        }

        lugarRepo.save(lugarOptional.get());

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("Se creó correctamente el comentario");

        return resultadoDTO;
    }

    @Override
    public ResultadoDTO responderComentario(RespuestaComentarioDTO respuestaComentarioDTO) throws Exception {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        Optional<Lugar> lugarOptional = lugarRepo.findLugarByIdAndComentarioId(respuestaComentarioDTO.idLugar(),
                respuestaComentarioDTO.idComentario());

        if(lugarOptional.isEmpty()){
            throw new Exception("No existe el comentario con id: " + respuestaComentarioDTO.idComentario() + " " +
                    "para el lugar con id: " + respuestaComentarioDTO.idLugar());
        }

        Lugar lugar = lugarOptional.get();
        lugar.getComentarios().get(0).setRespuesta(respuestaComentarioDTO.respuesta());
        lugar.getComentarios().get(0).setFechaRespuesta(respuestaComentarioDTO.fechaRespuesta());
        lugar.getComentarios().get(0).setIdClienteRespuesta(respuestaComentarioDTO.idUsuarioRespuesta());

        lugarRepo.save(lugar);

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("Se respondió correctamente el mensaje");

        return resultadoDTO;
    }

    @Override
    public ComentariosNegocioOutDTO listarComentariosNegocio(String idLugar) {
        List<Comentario> comentarios = lugarRepo.findComentariosByLugarId(idLugar);
        return new ComentariosNegocioOutDTO(comentarios);
    }

    @Override
    public Double calcularPromedioCalificacionesPorNegocio(String idLugar) {
        return lugarRepo.calcularPromedioCalificaciones(idLugar);
    }
}
