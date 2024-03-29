package co.edu.uniquindio.unilocal.servicios.impl;

import co.edu.uniquindio.unilocal.dto.*;
import co.edu.uniquindio.unilocal.servicios.interfaces.UsuarioServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioServicioImpl implements UsuarioServicio {

    @Override
    public void iniciarSesion(SesionDTO sesionDTO) throws Exception {

    }

    @Override
    public ResultadoDTO eliminarCuenta(String idCuenta) throws Exception {
        return null;
    }

    @Override
    public ResultadoDTO enviarLinkRecuperacion(String email) throws Exception {
        return null;
    }

    @Override
    public ResultadoDTO cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        return null;
    }

    @Override
    public ResultadoDTO registrarse(RegistroClienteDTO registroClienteDTO) throws Exception {
        return null;
    }

    @Override
    public ResultadoDTO editarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        return null;
    }
}
