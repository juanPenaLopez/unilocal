package co.edu.uniquindio.unilocal.controladores;

import co.edu.uniquindio.unilocal.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.unilocal.dto.CrearLugarDTO;
import co.edu.uniquindio.unilocal.dto.RegistrarRevisionDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.enums.Estado;
import co.edu.uniquindio.unilocal.modelo.Lugar;
import co.edu.uniquindio.unilocal.servicios.interfaces.LugarServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lugares")
@RequiredArgsConstructor
public class LugarController {

    private final LugarServicio lugarServicio;

    @PostMapping("/registrar-lugar")
    public ResultadoDTO crearNegocio(@RequestBody @Valid CrearLugarDTO crearLugarDTO){
        return lugarServicio.crearNegocio(crearLugarDTO);
    }

    @PutMapping("/editar-lugar")
    public ResultadoDTO actualizarNegocio(@RequestBody @Valid ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {
        return lugarServicio.actualizarNegocio(actualizarNegocioDTO);
    }

    @DeleteMapping("/eliminar-lugar")
    public ResultadoDTO eliminarNegocio(@PathVariable String idNegocio) throws Exception {
        return lugarServicio.eliminarNegocio(idNegocio);
    }

    @PostMapping("/registrar-revision-lugar")
    public ResultadoDTO registrarRevision(@RequestBody @Valid RegistrarRevisionDTO registrarRevisionDTO) throws Exception {
        return lugarServicio.registrarRevision(registrarRevisionDTO);
    }

    @GetMapping("/filtar-lugares-estado")
    public List<Lugar> filtrarPorEstado(@PathVariable Estado estado){
        return lugarServicio.filtrarPorEstado(estado);
    }
}
