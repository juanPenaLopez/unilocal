package co.edu.uniquindio.unilocal.test;

import co.edu.uniquindio.unilocal.dto.ActualizarEventoDTO;
import co.edu.uniquindio.unilocal.dto.EventoDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.enums.Estado;
import co.edu.uniquindio.unilocal.modelo.Evento;
import co.edu.uniquindio.unilocal.repositorios.EventoRepo;
import co.edu.uniquindio.unilocal.servicios.impl.EventoServicioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EventoServicioTest {

    @Mock
    private EventoRepo eventoRepo;

    @InjectMocks
    private EventoServicioImpl eventoServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearEvento() {
        EventoDTO eventoDTO = new EventoDTO("Evento Test", "Descripción Test", LocalDate.now(), LocalDate.now().plusDays(1), "1");
        when(eventoRepo.save(any(Evento.class))).thenAnswer(i -> i.getArguments()[0]);

        ResultadoDTO resultado = eventoServicio.crearEvento(eventoDTO);

        assertTrue(resultado.isExitoso());
        assertEquals("El evento se creó correctamente", resultado.getMensaje());
        verify(eventoRepo).save(any(Evento.class));
    }

    @Test
    void testModificarEventoNoExiste() {
        when(eventoRepo.findById("1")).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            eventoServicio.modificarEvento(new ActualizarEventoDTO("1", "Evento Modificado", "Descripción Modificada", LocalDate.now(), LocalDate.now().plusDays(1)));
        });

        assertEquals("No se encuentra el evento con id: 1", exception.getMessage());
    }

    @Test
    void testModificarEventoConFechaInvalida() {
        Evento eventoExistente = new Evento();
        eventoExistente.setFechaInicio(LocalDate.now());
        eventoExistente.setFechaFin(LocalDate.now().plusDays(2));
        when(eventoRepo.findById("1")).thenReturn(Optional.of(eventoExistente));

        Exception exception = assertThrows(Exception.class, () -> {
            eventoServicio.modificarEvento(new ActualizarEventoDTO("1", "Evento Modificado", "Descripción Modificada", LocalDate.now().plusDays(3), LocalDate.now().plusDays(4)));
        });

        assertTrue(exception.getMessage().contains("no pueden ser posteriores a la fecha fin originales"));
    }

    @Test
    void testModificarEventoExitoso() throws Exception {
        Evento eventoExistente = new Evento();
        eventoExistente.setFechaInicio(LocalDate.now());
        eventoExistente.setFechaFin(LocalDate.now().plusDays(2));
        when(eventoRepo.findById("1")).thenReturn(Optional.of(eventoExistente));
        when(eventoRepo.save(any(Evento.class))).thenAnswer(i -> i.getArguments()[0]);

        ActualizarEventoDTO dto = new ActualizarEventoDTO("1", "Evento Modificado", "Descripción Modificada", LocalDate.now(), LocalDate.now().plusDays(1));
        ResultadoDTO resultado = eventoServicio.modificarEvento(dto);

        assertTrue(resultado.isExitoso());
        assertEquals("Se actualizó de manera correcta el evento", resultado.getMensaje());
        verify(eventoRepo).save(any(Evento.class));
    }

    @Test
    void testEliminarEventoNoExiste() {
        when(eventoRepo.findById("1")).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> eventoServicio.eliminarEvento("1"));

        assertEquals("No existe el evento con id: 1", exception.getMessage());
    }

    @Test
    void testEliminarEventoExitoso() throws Exception {
        Evento eventoExistente = new Evento();
        eventoExistente.setEstadoEvento(Estado.ACTIVO);
        when(eventoRepo.findById("1")).thenReturn(Optional.of(eventoExistente));
        when(eventoRepo.save(any(Evento.class))).thenAnswer(i -> i.getArguments()[0]);

        ResultadoDTO resultado = eventoServicio.eliminarEvento("1");

        assertTrue(resultado.isExitoso());
        assertEquals("Se eliminó correctamente el evento", resultado.getMensaje());
        assertEquals(Estado.INACTIVO, eventoExistente.getEstadoEvento());
        verify(eventoRepo).save(any(Evento.class));
    }

}
