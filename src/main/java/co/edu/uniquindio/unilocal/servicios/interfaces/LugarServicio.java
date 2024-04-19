package co.edu.uniquindio.unilocal.servicios.interfaces;

import co.edu.uniquindio.unilocal.dto.*;
import co.edu.uniquindio.unilocal.enums.Estado;
import co.edu.uniquindio.unilocal.modelo.Lugar;

import java.util.List;

public interface LugarServicio {

    ResultadoDTO crearNegocio(CrearLugarDTO crearLugarDTO);

    ResultadoDTO actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;

    ResultadoDTO eliminarNegocio(String idNegocio) throws Exception;

    Lugar buscarNegocios(String idLugar) throws Exception;

    List<Lugar> filtrarPorEstado(Estado estado);

    List<Lugar> listarNegociosPropietario(String idUsuario);

    void cambiarEstado();

    ResultadoDTO registrarRevision(RegistrarRevisionDTO registrarRevisionDTO) throws Exception;
}
