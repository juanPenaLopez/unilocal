package co.edu.uniquindio.unilocal.test;

import co.edu.uniquindio.unilocal.dto.CambioPasswordDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.servicios.impl.ModeradorServicioImpl;
import co.edu.uniquindio.unilocal.servicios.interfaces.ModeradorServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


public class ModeradorServicioTest {

    @InjectMocks
    private ModeradorServicioImpl moderadorServicio;

    @Mock
    private ModeradorServicio moderadorServicioMock;

    @BeforeEach
    void setup() {
        // Configurar Mock aquí si es necesario.
    }

    @Test
    void testEliminarCuenta() throws Exception {
        // Configurar comportamiento simulado
        String idCuenta = "12345";
        ResultadoDTO resultadoEsperado = new ResultadoDTO();
        when(moderadorServicioMock.eliminarCuenta(idCuenta)).thenReturn(resultadoEsperado);

        // Realizar la llamada al método
        ResultadoDTO resultado = moderadorServicio.eliminarCuenta(idCuenta);

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    void testEnviarLinkRecuperacion() throws Exception {
        // Configurar comportamiento simulado
        String email = "test@example.com";
        ResultadoDTO resultadoEsperado = new ResultadoDTO();
        when(moderadorServicioMock.enviarLinkRecuperacion(email)).thenReturn(resultadoEsperado);

        // Realizar la llamada al método
        ResultadoDTO resultado = moderadorServicio.enviarLinkRecuperacion(email);

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    void testCambiarPassword() throws Exception {
        // Configurar comportamiento simulado
        CambioPasswordDTO cambioPasswordDTO = new CambioPasswordDTO("54321", "12345", "tokenDeValidación");
        ResultadoDTO resultadoEsperado = new ResultadoDTO();
        when(moderadorServicioMock.cambiarPassword(cambioPasswordDTO)).thenReturn(resultadoEsperado);

        // Realizar la llamada al método
        ResultadoDTO resultado = moderadorServicio.cambiarPassword(cambioPasswordDTO);

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals(resultadoEsperado, resultado);
    }
}