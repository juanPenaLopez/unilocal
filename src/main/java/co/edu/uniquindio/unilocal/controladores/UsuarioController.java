package co.edu.uniquindio.unilocal.controladores;

import co.edu.uniquindio.unilocal.dto.ActualizarClienteDTO;
import co.edu.uniquindio.unilocal.dto.RegistroClienteDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.modelo.Usuario;
import co.edu.uniquindio.unilocal.servicios.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServicio usuarioServicio;

    @DeleteMapping("/eliminar-usuario")
    public ResultadoDTO eliminarCuenta(@PathVariable String idUsuario) throws Exception {
        return usuarioServicio.eliminarCuenta(idUsuario);
    }

    @PostMapping("/registrar-usuario")
    public ResultadoDTO registrarse(@Valid @RequestBody RegistroClienteDTO registroClienteDTO) throws Exception {
        return usuarioServicio.registrarse(registroClienteDTO);
    }

    @GetMapping("/consultar-perfil")
    public Usuario consultarPerfil(@RequestParam String idUsuario) throws Exception {
        return usuarioServicio.consultarPerfil(idUsuario);
    }

    @PutMapping("/editar-perfil")
    public ResultadoDTO editarPerfil(@RequestBody @Valid ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        return usuarioServicio.editarPerfil(actualizarClienteDTO);
    }
}
