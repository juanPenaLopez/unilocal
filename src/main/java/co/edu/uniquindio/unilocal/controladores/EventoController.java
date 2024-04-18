package co.edu.uniquindio.unilocal.controladores;

import co.edu.uniquindio.unilocal.dto.ActualizarEventoDTO;
import co.edu.uniquindio.unilocal.dto.EventoDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.servicios.interfaces.EventoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoServicio eventoServicio;

    @PostMapping("/crear-evento")
    public ResultadoDTO crearEvento(EventoDTO eventoDTO){
        return eventoServicio.crearEvento(eventoDTO);
    }

    @DeleteMapping("/eliminar-evento")
    public ResultadoDTO eliminarEvento(String idEvento) throws Exception {
        return eventoServicio.eliminarEvento(idEvento);
    }

    @PutMapping("/modificar-evento")
    public ResultadoDTO modificarEvento(ActualizarEventoDTO actualizarEventoDTO) throws Exception {
        return eventoServicio.modificarEvento(actualizarEventoDTO);
    }
}
