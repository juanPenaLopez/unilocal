package co.edu.uniquindio.unilocal.servicios.interfaces;


import co.edu.uniquindio.unilocal.dto.ActualizarClienteDTO;
import co.edu.uniquindio.unilocal.dto.RegistroClienteDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.modelo.Usuario;

public interface UsuarioServicio extends CuentaServicio {

    ResultadoDTO registrarse(RegistroClienteDTO registroClienteDTO)throws Exception;

    ResultadoDTO editarPerfil(ActualizarClienteDTO actualizarClienteDTO)throws Exception;

    Usuario consultarPerfil(String idUsuario) throws Exception;

}
