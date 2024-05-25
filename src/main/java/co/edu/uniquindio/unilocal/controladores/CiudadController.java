package co.edu.uniquindio.unilocal.controladores;

import co.edu.uniquindio.unilocal.modelo.Ciudad;
import co.edu.uniquindio.unilocal.servicios.interfaces.CiudadServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ciudad")
@RequiredArgsConstructor
public class CiudadController {

    private final CiudadServicio ciudadService;

    @GetMapping("/consultar-ciudades")
    public ResponseEntity<List<Ciudad>> obtenerCiudadesParaSelect() {
        List<Ciudad> ciudades = ciudadService.obtenerTodasLasCiudades();
        return ResponseEntity.ok(ciudades);
    }
}
