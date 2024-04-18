package co.edu.uniquindio.unilocal.controladores;

import co.edu.uniquindio.unilocal.dto.LoginDTO;
import co.edu.uniquindio.unilocal.dto.MensajeDTO;
import co.edu.uniquindio.unilocal.dto.TokenDTO;
import co.edu.uniquindio.unilocal.servicios.interfaces.AutenticacionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {

    private final AutenticacionServicio autenticacionServicio;
    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            TokenDTO tokenDTO = autenticacionServicio.iniciarSesionCliente(loginDTO);

            if (tokenDTO != null) {
                return ResponseEntity.ok(new MensajeDTO<>(true, tokenDTO));
            } else {
                tokenDTO = autenticacionServicio.iniciarSesionModerador(loginDTO);
                if (tokenDTO != null) {
                    return ResponseEntity.ok(new MensajeDTO<>(true, tokenDTO));
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MensajeDTO<>(false, null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajeDTO<>(false, null));
        }
    }
}
