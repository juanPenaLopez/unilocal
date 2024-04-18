package co.edu.uniquindio.unilocal.servicios.impl;

import co.edu.uniquindio.unilocal.dto.LoginDTO;
import co.edu.uniquindio.unilocal.dto.TokenDTO;
import co.edu.uniquindio.unilocal.modelo.Cuenta;
import co.edu.uniquindio.unilocal.modelo.Moderador;
import co.edu.uniquindio.unilocal.modelo.Usuario;
import co.edu.uniquindio.unilocal.repositorios.CuentaRepo;
import co.edu.uniquindio.unilocal.repositorios.ModeradorRepo;
import co.edu.uniquindio.unilocal.repositorios.UsuarioRepo;
import co.edu.uniquindio.unilocal.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.unilocal.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {

    private final UsuarioRepo usuarioRepo;
    private final JWTUtils jwtUtils;
    private final ModeradorRepo moderadorRepo;

    @Override
    public TokenDTO iniciarSesionCliente(LoginDTO loginDTO) throws Exception {

        Usuario usuario = usuarioRepo.findByEmail(loginDTO.correo());

        if (usuario == null) {
            throw new Exception("El correo no se encuentra registrado");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if( !passwordEncoder.matches(loginDTO.contrasena(), usuario.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("rol", "usuario");
        map.put("nombre", usuario.getNombreCompleto());
        map.put("id", usuario.getId());
        return new TokenDTO( jwtUtils.generarToken(usuario.getEmail(), map) );
    }

    @Override
    public TokenDTO iniciarSesionModerador(LoginDTO loginDTO) throws Exception {

        Moderador moderador = moderadorRepo.findByEmail(loginDTO.correo());

        if (moderador == null) {
            throw new Exception("El correo no se encuentra registrado");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if( !passwordEncoder.matches(loginDTO.contrasena(), moderador.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("rol", "moderador");
        map.put("nombre", moderador.getCodigo());
        map.put("id", moderador.getId());
        return new TokenDTO( jwtUtils.generarToken(moderador.getEmail(), map) );
    }
}
