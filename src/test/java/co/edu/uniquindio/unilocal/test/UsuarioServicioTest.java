package co.edu.uniquindio.unilocal.test;

import co.edu.uniquindio.unilocal.dto.CambioPasswordDTO;
import co.edu.uniquindio.unilocal.dto.EmailDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.enums.Estado;
import co.edu.uniquindio.unilocal.modelo.Usuario;
import co.edu.uniquindio.unilocal.repositorios.CiudadRepo;
import co.edu.uniquindio.unilocal.repositorios.UsuarioRepo;
import co.edu.uniquindio.unilocal.servicios.impl.UsuarioServicioImpl;
import co.edu.uniquindio.unilocal.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unilocal.utils.JWTUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioServicioTest {

    @Mock
    private UsuarioRepo usuarioRepo;
    @Mock
    private CiudadRepo ciudadRepo;
    @Mock
    private EmailServicio emailServicio;
    @Mock
    private JWTUtils jwtUtils;

    @InjectMocks
    private UsuarioServicioImpl usuarioServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cuandoIdNoExisteLanzaExcepcion() {
        when(usuarioRepo.findById(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            usuarioServicio.eliminarCuenta("123");
        });

        assertEquals("No existe el usuario", exception.getMessage());
    }

    @Test
    void cuandoIdExisteEliminaUsuario() throws Exception {
        Usuario usuarioMock = new Usuario();
        usuarioMock.setEstado(Estado.ACTIVO);
        when(usuarioRepo.findById(anyString())).thenReturn(Optional.of(usuarioMock));

        ResultadoDTO resultado = usuarioServicio.eliminarCuenta("123");
        assertEquals(true, resultado.isExitoso());
        assertEquals("Se eliminó correctamente el usuario", resultado.getMensaje());
        assertEquals(Estado.INACTIVO, usuarioMock.getEstado());

        verify(usuarioRepo).save(any(Usuario.class)); // Verificar que se llamó al método save
    }

    @Test
    void enviarLinkRecuperacionEnviaCorreo() throws Exception {
        when(emailServicio.enviarCorreo(any(EmailDTO.class))).thenReturn(new ResultadoDTO(true, "Correo enviado"));

        ResultadoDTO resultado = usuarioServicio.enviarLinkRecuperacion("test@test.com");
        assertTrue(resultado.isExitoso());
        assertEquals("Correo enviado", resultado.getMensaje());

        verify(emailServicio).enviarCorreo(any(EmailDTO.class)); // Verifica que se llamó al servicio de correo
    }

    @Test
    void cambiarPasswordTokenInvalidoLanzaExcepcion() {
        when(jwtUtils.parseJwt(anyString())).thenThrow(new ExpiredJwtException(null, null, "Token expired"));
        CambioPasswordDTO dto = new CambioPasswordDTO("newPass", "1", "invalidToken");

        Exception exception = assertThrows(Exception.class, () -> {
            usuarioServicio.cambiarPassword(dto);
        });

        assertEquals("Token inválido o expirado.", exception.getMessage());
    }

    @Test
    void cambiarPasswordExitoso() throws Exception {
        Usuario usuarioMock = new Usuario();
        when(usuarioRepo.findById(anyString())).thenReturn(Optional.of(usuarioMock));
        when(jwtUtils.parseJwt(anyString())).thenReturn(null); // Simular token válido
        when(usuarioRepo.save(any(Usuario.class))).thenReturn(usuarioMock);

        CambioPasswordDTO dto = new CambioPasswordDTO("newPass", "1", "validToken");
        ResultadoDTO resultado = usuarioServicio.cambiarPassword(dto);

        assertTrue(resultado.isExitoso());
        assertEquals("Contraseña actualizada correctamente.", resultado.getMensaje());
        assertNotNull(usuarioMock.getPassword());

        verify(usuarioRepo).save(any(Usuario.class));
    }
}
