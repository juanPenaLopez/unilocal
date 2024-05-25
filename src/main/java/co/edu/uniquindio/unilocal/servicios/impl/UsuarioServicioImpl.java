package co.edu.uniquindio.unilocal.servicios.impl;

import co.edu.uniquindio.unilocal.dto.*;
import co.edu.uniquindio.unilocal.enums.Estado;
import co.edu.uniquindio.unilocal.modelo.Usuario;
import co.edu.uniquindio.unilocal.repositorios.CiudadRepo;
import co.edu.uniquindio.unilocal.repositorios.UsuarioRepo;
import co.edu.uniquindio.unilocal.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unilocal.servicios.interfaces.UsuarioServicio;
import co.edu.uniquindio.unilocal.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private EmailServicio emailServicio;
    private JWTUtils jwtUtils;

    @Autowired
    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, CiudadRepo ciudadRepo, EmailServicio emailServicio) {
        this.usuarioRepo = usuarioRepo;
        this.ciudadRepo = ciudadRepo;
        this.emailServicio = emailServicio;
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

        String token = generarToken();
        String linkRecuperacion = "http://localhost:4200/recuperar-contrasena-link";

        String asunto = "Recuperación de Contraseña";
        String cuerpo = "<h1>Recuperación de Contraseña</h1>"
                + "<p>Haz click en el siguiente enlace para restablecer tu contraseña:</p>"
                + "<a href='" + linkRecuperacion + "'>Restablecer Contraseña</a>";

        EmailDTO emailDTO = new EmailDTO(asunto, cuerpo, email);

        return emailServicio.enviarCorreo(emailDTO);
    }

    @Override
    public ResultadoDTO cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Usuario usuario = usuarioRepo.findById(cambioPasswordDTO.id()).orElseThrow(() ->
                new Exception("No se encuentra el usuario con el id proporcionado.")
        );

        usuario.setPassword(passwordEncoder.encode(cambioPasswordDTO.passwordNueva()));

        usuarioRepo.save(usuario);

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("Contraseña actualizada correctamente.");

        return resultadoDTO;
    }

    private boolean tokenValido(String token) {
        try {
            // Uso del método parseJwt para validar el token
            Claims claims = jwtUtils.parseJwt(token).getBody();
            // Aquí puedes realizar más validaciones como verificar si el token pertenece al usuario, etc.
            return true;
        } catch (ExpiredJwtException | IllegalArgumentException e) {
            return false;
        }
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
        ResultadoDTO resultadoDTO = new ResultadoDTO();

        Optional<Usuario> usuarioBuscado = usuarioRepo.findById(actualizarClienteDTO.id());

        if(usuarioBuscado.isEmpty()){
            throw new Exception("No se encuentra el usuario con id: " + actualizarClienteDTO.id());
        }

        Usuario usuario = usuarioBuscado.get();

        usuario.setNombreCompleto(actualizarClienteDTO.nombre());
        usuario.setIdCiudad(actualizarClienteDTO.ciudadResidencia());
        usuario.setEmail(actualizarClienteDTO.email());
        usuario.setUrlFoto(actualizarClienteDTO.fotoPerfil());

        usuarioRepo.save(usuario);

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("Se actualizó correctamente el usuario");

        return resultadoDTO;
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

    private String generarToken() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
}
