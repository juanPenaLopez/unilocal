package co.edu.uniquindio.unilocal.test;

import co.edu.uniquindio.unilocal.dto.CambioPasswordDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.servicios.interfaces.ModeradorServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ModeradorServicioImplTest {

    @Autowired
    private ModeradorServicio moderadorServicio;

    @BeforeEach
    void setUp() {
    }

    @Test
    void eliminarCuentaTest() throws Exception {
        // Arrange
        String idCuenta = "123";

        // Act
        ResultadoDTO resultadoDTO = moderadorServicio.eliminarCuenta(idCuenta);

        // Assert
        assertEquals("La cuenta ha sido eliminada exitosamente", resultadoDTO.getMensaje());
        assertEquals(true, resultadoDTO.isExitoso());
    }

    @Test
    void enviarLinkRecuperacionTest() throws Exception {
        // Arrange
        String email = "test@uniquindio.edu.co";

        // Act
        ResultadoDTO resultadoDTO = moderadorServicio.enviarLinkRecuperacion(email);

        // Assert
        assertEquals("Se ha enviado un enlace de recuperación al correo electrónico", resultadoDTO.getMensaje());
        assertEquals(true, resultadoDTO.isExitoso());
    }

    @Test
    void cambiarPasswordTest() throws Exception {
        // Arrange
        String idCuenta = "123";
        CambioPasswordDTO cambioPasswordDTO = new CambioPasswordDTO(idCuenta, "123456", "654321");
        // Act
        ResultadoDTO resultadoDTO = moderadorServicio.cambiarPassword(cambioPasswordDTO);

        // Assert
        assertEquals("La contraseña ha sido cambiada exitosamente", resultadoDTO.getMensaje());
        assertEquals(true, resultadoDTO.isExitoso());
    }
}