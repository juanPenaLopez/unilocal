package co.edu.uniquindio.unilocal.controladores;

import co.edu.uniquindio.unilocal.dto.ComentariosNegocioOutDTO;
import co.edu.uniquindio.unilocal.dto.CrearComentarioDTO;
import co.edu.uniquindio.unilocal.dto.RespuestaComentarioDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.servicios.interfaces.ComentarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioServicio comentarioServicio;

    @PostMapping("/crear-comentario")
    public ResultadoDTO crearComentario(@Valid @RequestBody CrearComentarioDTO crearComentarioDTO) throws Exception {
        return comentarioServicio.crearComentario(crearComentarioDTO);
    }

    @PutMapping("/responder-comentario")
    public ResultadoDTO responderComentario(@Valid @RequestBody RespuestaComentarioDTO respuestaComentarioDTO) throws Exception {
        return  comentarioServicio.responderComentario(respuestaComentarioDTO);
    }

    @GetMapping("/listar-comentarios-negocio")
    public ComentariosNegocioOutDTO listarComentariosNegocio(@RequestParam String idLugar) {
        return comentarioServicio.listarComentariosNegocio(idLugar);
    }

    @GetMapping("/calcular-promedio-negocio")
    public Double calcularPromedioCalificacionesPorNegocio(@RequestParam String idLugar) {
        return comentarioServicio.calcularPromedioCalificacionesPorNegocio(idLugar);
    }
}
