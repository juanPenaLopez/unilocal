package co.edu.uniquindio.unilocal.servicios.impl;

import co.edu.uniquindio.unilocal.dto.LoginDTO;
import co.edu.uniquindio.unilocal.dto.TokenDTO;
import co.edu.uniquindio.unilocal.modelo.Cuenta;
import co.edu.uniquindio.unilocal.modelo.Moderador;
import co.edu.uniquindio.unilocal.modelo.Usuario;
import co.edu.uniquindio.unilocal.repositorios.CuentaRepo;
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

    private final CuentaRepo cuentaRepo;
    private final JWTUtils jwtUtils;

    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Cuenta> cuentaOptional = cuentaRepo.findById(loginDTO.correo());
        if(cuentaOptional.isEmpty()){
            throw new Exception("No existe el correo ingresado");
        }
        Cuenta cuenta = cuentaOptional.get();
        if( !passwordEncoder.matches(loginDTO.contrasena(), cuenta.getPassword()) ){
            throw new Exception("La contraseña ingresada es incorrecta");
        }
        return new TokenDTO( crearToken(cuenta) );
    }

    private String crearToken(Cuenta cuenta){
        String rol = "";
        String nombre = "";

        if( cuenta instanceof Usuario){
            rol = "usuario";
            nombre = ((Usuario) cuenta).getNombreCompleto();
        }else if( cuenta instanceof Moderador){
            rol = "moderador";
            nombre = ((Moderador) cuenta).getCodigo();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("nombre", nombre);
        map.put("id", cuenta.getId());
        return jwtUtils.generarToken(cuenta.getEmail(), map);
    }
}
