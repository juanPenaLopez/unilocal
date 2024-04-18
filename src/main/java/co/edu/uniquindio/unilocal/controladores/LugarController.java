package co.edu.uniquindio.unilocal.controladores;

import co.edu.uniquindio.unilocal.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.unilocal.dto.CrearLugarDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.servicios.interfaces.LugarServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lugares")
@RequiredArgsConstructor
public class LugarController {

    private final LugarServicio lugarServicio;

    @PostMapping("/registrar-lugar")
    public ResultadoDTO crearNegocio(CrearLugarDTO crearLugarDTO){
        return lugarServicio.crearNegocio(crearLugarDTO);
    }

    @PutMapping("/editar-lugar")
    public ResultadoDTO actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {
        return lugarServicio.actualizarNegocio(actualizarNegocioDTO);
    }

    @DeleteMapping("/eliminar-lugar")
    public ResultadoDTO eliminarNegocio(String idNegocio) throws Exception {
        return lugarServicio.eliminarNegocio(idNegocio);
    }
}
