package co.edu.uniquindio.unilocal.controladores;

import co.edu.uniquindio.unilocal.servicios.interfaces.ReservaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaServicio reservaServicio;


}
