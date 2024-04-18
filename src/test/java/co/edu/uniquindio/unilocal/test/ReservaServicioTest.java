package co.edu.uniquindio.unilocal.test;

import co.edu.uniquindio.unilocal.dto.CrearReservaDTO;
import co.edu.uniquindio.unilocal.dto.ModificarReservaDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.enums.EstadoReserva;
import co.edu.uniquindio.unilocal.modelo.Reserva;
import co.edu.uniquindio.unilocal.repositorios.ReservaRepo;
import co.edu.uniquindio.unilocal.servicios.impl.ReservaServicioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReservaServicioTest {

    @Mock
    private ReservaRepo reservaRepo;

    @InjectMocks
    private ReservaServicioImpl reservaServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearReserva() {
        CrearReservaDTO dto = new CrearReservaDTO("1", "1", LocalDate.now(), LocalTime.now(), 4);
        when(reservaRepo.save(any(Reserva.class))).thenAnswer(i -> i.getArguments()[0]);

        ResultadoDTO resultado = reservaServicio.crearReserva(dto);

        assertTrue(resultado.isExitoso());
        assertEquals("Se creó correctamente la reserva", resultado.getMensaje());
        verify(reservaRepo).save(any(Reserva.class));
    }

    @Test
    void testModificarReservaNoExiste() {
        ModificarReservaDTO dto = new ModificarReservaDTO("1", LocalDate.now(), LocalTime.now(), 4);
        when(reservaRepo.findById("1")).thenReturn(Optional.empty());

        ResultadoDTO resultado = reservaServicio.modificarReserva(dto);

        assertFalse(resultado.isExitoso());
        assertEquals("No existe la reserva con id: 1", resultado.getMensaje());
    }

    @Test
    void testModificarReservaConHoraMuyCercana() {
        Reserva reservaExistente = new Reserva();
        reservaExistente.setFechaReserva(LocalDate.now());
        reservaExistente.setHoraReserva(LocalTime.of(12, 0));
        when(reservaRepo.findById("1")).thenReturn(Optional.of(reservaExistente));

        ModificarReservaDTO dto = new ModificarReservaDTO("1", LocalDate.now(), LocalTime.of(12, 20), 4);

        ResultadoDTO resultado = reservaServicio.modificarReserva(dto);

        assertFalse(resultado.isExitoso());
        assertEquals("La nueva hora de reserva debe ser al menos 30 minutos diferente a la hora reservada existente.", resultado.getMensaje());
    }

    @Test
    void testModificarReservaExitosa() {
        Reserva reservaExistente = new Reserva();
        reservaExistente.setFechaReserva(LocalDate.now());
        reservaExistente.setHoraReserva(LocalTime.of(12, 0));
        when(reservaRepo.findById("1")).thenReturn(Optional.of(reservaExistente));

        ModificarReservaDTO dto = new ModificarReservaDTO("1", LocalDate.now(), LocalTime.of(13, 0), 4);
        when(reservaRepo.save(any(Reserva.class))).thenAnswer(i -> i.getArguments()[0]);

        ResultadoDTO resultado = reservaServicio.modificarReserva(dto);

        assertTrue(resultado.isExitoso());
        assertEquals("Se modificó correctamente la reserva", resultado.getMensaje());
        verify(reservaRepo).save(any(Reserva.class));
    }

    @Test
    void testCancelarReservaNoExiste() {
        when(reservaRepo.findById("1")).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            reservaServicio.cancelarReserva("1");
        });

        assertEquals("No existe la reserva con id: 1", exception.getMessage());
    }

    @Test
    void testCancelarReservaExitosa() throws Exception {
        Reserva reservaExistente = new Reserva();
        when(reservaRepo.findById("1")).thenReturn(Optional.of(reservaExistente));
        when(reservaRepo.save(any(Reserva.class))).thenAnswer(i -> i.getArguments()[0]);

        ResultadoDTO resultado = reservaServicio.cancelarReserva("1");

        assertTrue(resultado.isExitoso());
        assertEquals("La reserva fue cancelada con éxito", resultado.getMensaje());
        assertEquals(EstadoReserva.CANCELADA, reservaExistente.getEstadoReserva());
        verify(reservaRepo).save(any(Reserva.class));
    }
}
