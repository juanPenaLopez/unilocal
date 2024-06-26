package co.edu.uniquindio.unilocal.controladores;

import co.edu.uniquindio.unilocal.dto.CrearReservaDTO;
import co.edu.uniquindio.unilocal.dto.ModificarReservaDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.enums.EstadoReserva;
import co.edu.uniquindio.unilocal.modelo.Reserva;
import co.edu.uniquindio.unilocal.servicios.interfaces.ReservaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaServicio reservaServicio;

    @PostMapping("/crear-reserva")
    public ResultadoDTO crearReserva(@RequestBody CrearReservaDTO crearReservaDTO){
        return reservaServicio.crearReserva(crearReservaDTO);
    }

    @DeleteMapping("/cancelar-reserva")
    public ResultadoDTO cancelarReserva(@RequestParam String idReserva) throws Exception{
        return reservaServicio.cancelarReserva(idReserva);
    }

    @GetMapping("/consultar-reservas-estado-usuario")
    public List<Reserva> consultarReservasPorEstadoUsuario(@RequestParam String idUsuario,
                                                           @RequestParam EstadoReserva estadoReserva){
        return reservaServicio.consultarReservasPorEstadoUsuario(idUsuario, estadoReserva);
    }

    @PutMapping("/modificar-reserva")
    public ResultadoDTO modificarReserva(@RequestBody ModificarReservaDTO modificarReservaDTO){
        return reservaServicio.modificarReserva(modificarReservaDTO);
    }

    @GetMapping("/consultar-reserva-lugar-usuario")
    public List<Reserva> consultarReservaPorLugarUsuario(@RequestParam String idUsuario,
                                                         @RequestParam String idLugar){
        return reservaServicio.consultarReservaPorLugarUsuario(idUsuario, idLugar);
    }

    @GetMapping("/consultar-reserva-usuario")
    public List<Reserva> consultarReservaPorUsuario(@RequestParam String idUsuario) {
        return reservaServicio.consultarReservaPorUsuario(idUsuario);
    }
}
