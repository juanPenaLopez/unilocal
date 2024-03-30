package co.edu.uniquindio.unilocal.servicios.impl;

import co.edu.uniquindio.unilocal.dto.*;
import co.edu.uniquindio.unilocal.enums.Estado;
import co.edu.uniquindio.unilocal.modelo.Ciudad;
import co.edu.uniquindio.unilocal.modelo.Usuario;
import co.edu.uniquindio.unilocal.repositorios.CiudadRepo;
import co.edu.uniquindio.unilocal.repositorios.UsuarioRepo;
import co.edu.uniquindio.unilocal.servicios.interfaces.UsuarioServicio;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final CiudadRepo ciudadRepo;

    @Override
    public void iniciarSesion(SesionDTO sesionDTO) throws Exception {

    }

    @Override
    public ResultadoDTO eliminarCuenta(String idUsuario) throws Exception {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        Optional<Usuario> usuarioOptional = usuarioRepo.findById(idUsuario);

        if(usuarioOptional.isEmpty()){
            throw new Exception("No existe el usuario");
        }

        usuarioOptional.get().setEstado(Estado.INACTIVO);

        usuarioRepo.save(usuarioOptional.get());

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("Se eliminó correctamente el usuario");
        return resultadoDTO;
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

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        if(estaRepetidoEmail(registroClienteDTO.email())){
            resultadoDTO.setExitoso(false);
            resultadoDTO.getListaMensajesError().add("El correo " + registroClienteDTO.email() +
                    " ya se encuentra en uso");
            return resultadoDTO;
        }

        if(estaRepetidoNickName(registroClienteDTO.nickname())){
            resultadoDTO.setExitoso(false);
            resultadoDTO.getListaMensajesError().add("El nickname: " + registroClienteDTO.nickname() +
                    ", ya se encuentra en uso");
            return resultadoDTO;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String contraseñaCifrada = passwordEncoder.encode(registroClienteDTO.password());

        Usuario usuario = new Usuario();
        usuario.setEmail(registroClienteDTO.email());
        usuario.setEstado(Estado.ACTIVO);
        usuario.setNombreCompleto(registroClienteDTO.nombre());
        usuario.setUrlFoto(registroClienteDTO.fotoPerfil());
        usuario.setNickname(registroClienteDTO.nickname());
        usuario.setIdCiudad(registroClienteDTO.ciudadResidencia());
        usuario.setPassword(contraseñaCifrada);

        usuarioRepo.save(usuario);

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("Se creó correctamente el usuario");

        return resultadoDTO;
    }

    @Override
    public ResultadoDTO editarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        return null;
    }

    @Override
    public Usuario consultarPerfil(String idUsuario) throws Exception {
        Optional<Usuario> usuarioBuscado = usuarioRepo.findById(idUsuario);

        if(usuarioBuscado.isEmpty()){
            throw new Exception("No se encuentra el usuario con id: " + idUsuario);
        }

        return usuarioBuscado.get();
    }

    private boolean estaRepetidoEmail(@NotBlank @Email String email){
        return usuarioRepo.findByEmail(email) != null;
    }

    private boolean estaRepetidoNickName(@NotBlank String nickname){
        return usuarioRepo.findByNickname(nickname) != null;
    }
}
