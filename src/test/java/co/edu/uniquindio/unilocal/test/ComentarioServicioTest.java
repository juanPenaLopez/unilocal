package co.edu.uniquindio.unilocal.test;

import co.edu.uniquindio.unilocal.dto.CrearComentarioDTO;
import co.edu.uniquindio.unilocal.dto.RespuestaComentarioDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.modelo.Comentario;
import co.edu.uniquindio.unilocal.modelo.Lugar;
import co.edu.uniquindio.unilocal.repositorios.LugarRepo;
import co.edu.uniquindio.unilocal.servicios.impl.ComentarioServicioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ComentarioServicioTest {

    @Mock
    private LugarRepo lugarRepo;

    @InjectMocks
    private ComentarioServicioImpl comentarioServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearComentarioCuandoLugarNoExiste() {
        CrearComentarioDTO comentarioDTO = new CrearComentarioDTO(UUID.randomUUID().toString(),
                "Mensaje", LocalDateTime.now(), (short) 5, "1", "1");
        when(lugarRepo.findById(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> comentarioServicio.crearComentario(comentarioDTO));
        assertEquals("El id del lugar no puede venir nulo", exception.getMessage());
    }

    @Test
    void testCrearComentarioExitoso() throws Exception {
        String lugarId = UUID.randomUUID().toString();

        CrearComentarioDTO comentarioDTO = new CrearComentarioDTO(
                lugarId, "Mensaje", LocalDateTime.now(), (short) 5, "1", "1");

        Lugar lugar = new Lugar();
        lugar.setComentarios(new ArrayList<>());
        when(lugarRepo.findById(lugarId)).thenReturn(Optional.of(lugar));
        when(lugarRepo.save(any(Lugar.class))).thenReturn(lugar);

        ResultadoDTO resultado = comentarioServicio.crearComentario(comentarioDTO);

        assertTrue(resultado.isExitoso());
        assertEquals("Se creó correctamente el comentario", resultado.getMensaje());
        assertEquals(1, lugar.getComentarios().size());
    }

    @Test
    void testResponderComentarioNoExiste() {
        RespuestaComentarioDTO respuestaDTO = new RespuestaComentarioDTO(
                "1", "Respuesta", LocalDateTime.now(), "1", "1");
        when(lugarRepo.findLugarByIdAndComentarioId(anyString(), anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> comentarioServicio.responderComentario(respuestaDTO));
        assertTrue(exception.getMessage().contains("No existe el comentario con id:"));
    }

    @Test
    void testResponderComentarioExitoso() throws Exception {
        RespuestaComentarioDTO respuestaDTO = new RespuestaComentarioDTO(
                "1", "Respuesta", LocalDateTime.now(), "1", "1");

        Lugar lugar = new Lugar();
        Comentario comentario = new Comentario();
        lugar.setComentarios(new ArrayList<>(List.of(comentario)));
        when(lugarRepo.findLugarByIdAndComentarioId("1", "1")).thenReturn(Optional.of(lugar));
        when(lugarRepo.save(any(Lugar.class))).thenReturn(lugar);

        ResultadoDTO resultado = comentarioServicio.responderComentario(respuestaDTO);

        assertTrue(resultado.isExitoso());
        assertEquals("Se respondió correctamente el mensaje", resultado.getMensaje());
    }
}
