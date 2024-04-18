package co.edu.uniquindio.unilocal.servicios.interfaces;

import co.edu.uniquindio.unilocal.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.unilocal.dto.ConsultarNegocioDTO;
import co.edu.uniquindio.unilocal.dto.CrearLugarDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.modelo.Lugar;

import java.util.List;

public interface LugarServicio {

    ResultadoDTO crearNegocio(CrearLugarDTO crearLugarDTO);

    ResultadoDTO actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;

    ResultadoDTO eliminarNegocio(String idNegocio) throws Exception;

    ConsultarNegocioDTO buscarNegocios(String idLugar);

    void filtrarPorEstado();

    List<Lugar> listarNegociosPropietario(String idUsuario);

    void cambiarEstado();

    void registrarRevision();
}
