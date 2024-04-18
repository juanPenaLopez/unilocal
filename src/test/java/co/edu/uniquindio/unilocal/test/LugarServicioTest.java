package co.edu.uniquindio.unilocal.test;

import co.edu.uniquindio.unilocal.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.unilocal.dto.CrearLugarDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.enums.CategoriaLugar;
import co.edu.uniquindio.unilocal.enums.Estado;
import co.edu.uniquindio.unilocal.modelo.Lugar;
import co.edu.uniquindio.unilocal.repositorios.LugarRepo;
import co.edu.uniquindio.unilocal.servicios.impl.LugarServicioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LugarServicioTest {

    @Mock
    private LugarRepo lugarRepo;

    @InjectMocks
    private LugarServicioImpl lugarServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearNegocioConNombreRepetido() {
        CrearLugarDTO dto = new CrearLugarDTO("Cafe", CategoriaLugar.CAFETERIA, null, "Descripcion", null, null, null);
        when(lugarRepo.findByNombre("Cafe")).thenReturn(new Lugar());

        ResultadoDTO resultado = lugarServicio.crearNegocio(dto);

        assertFalse(resultado.isExitoso());
        assertTrue(resultado.getListaMensajesError().contains("El nombre del negocio Cafe ya se encuentra en uso"));
    }

    @Test
    void testCrearNegocioExitoso() {
        CrearLugarDTO dto = new CrearLugarDTO("Cafe", CategoriaLugar.CAFETERIA, null, "Descripcion", null, null, null);
        when(lugarRepo.findByNombre("Cafe")).thenReturn(null);
        when(lugarRepo.save(any(Lugar.class))).thenAnswer(i -> i.getArguments()[0]);

        ResultadoDTO resultado = lugarServicio.crearNegocio(dto);

        assertTrue(resultado.isExitoso());
        assertEquals("Se creó correctamente el lugar", resultado.getMensaje());
        verify(lugarRepo).save(any(Lugar.class));
    }

    @Test
    void testActualizarNegocioNoExiste() {
        when(lugarRepo.findById("1")).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            lugarServicio.actualizarNegocio(new ActualizarNegocioDTO("1", "Cafe", "Cafeteria", "Descripcion", "Ubicacion", null, null, null));
        });

        assertEquals("No se encuentra el lugar con id: 1", exception.getMessage());
    }

    @Test
    void testActualizarNegocioExitoso() throws Exception {
        Lugar lugarExistente = new Lugar();
        when(lugarRepo.findById("1")).thenReturn(Optional.of(lugarExistente));
        when(lugarRepo.save(any(Lugar.class))).thenAnswer(i -> i.getArguments()[0]);

        ActualizarNegocioDTO dto = new ActualizarNegocioDTO("1", "Cafe Updated", "Cafeteria", "Descripcion Updated", "Ubicacion Updated", null, null, null);
        ResultadoDTO resultado = lugarServicio.actualizarNegocio(dto);

        assertTrue(resultado.isExitoso());
        assertEquals("El lugar fue actualizado correctamente", resultado.getMensaje());
        assertEquals("Cafe Updated", lugarExistente.getNombre());
        verify(lugarRepo).save(any(Lugar.class));
    }

    @Test
    void testEliminarNegocioNoExiste() {
        when(lugarRepo.findById("1")).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> lugarServicio.eliminarNegocio("1"));

        assertEquals("No existe el lugar", exception.getMessage());
    }

    @Test
    void testEliminarNegocioExitoso() throws Exception {
        Lugar lugarExistente = new Lugar();
        lugarExistente.setEstado(Estado.ACTIVO);
        when(lugarRepo.findById("1")).thenReturn(Optional.of(lugarExistente));
        when(lugarRepo.save(any(Lugar.class))).thenAnswer(i -> i.getArguments()[0]);

        ResultadoDTO resultado = lugarServicio.eliminarNegocio("1");

        assertTrue(resultado.isExitoso());
        assertEquals("Se eliminó correctamente el lugar", resultado.getMensaje());
        assertEquals(Estado.INACTIVO, lugarExistente.getEstado());
        verify(lugarRepo).save(any(Lugar.class));
    }
}
