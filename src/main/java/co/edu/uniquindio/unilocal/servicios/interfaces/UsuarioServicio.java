package co.edu.uniquindio.unilocal.servicios.interfaces;


import co.edu.uniquindio.unilocal.dto.ActualizarClienteDTO;
import co.edu.uniquindio.unilocal.dto.RegistroClienteDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;

public interface UsuarioServicio extends CuentaServicio {

    ResultadoDTO registrarse(RegistroClienteDTO registroClienteDTO)throws Exception;

    ResultadoDTO editarPerfil(ActualizarClienteDTO actualizarClienteDTO)throws Exception;

}
