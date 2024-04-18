package co.edu.uniquindio.unilocal.servicios.impl;

import co.edu.uniquindio.unilocal.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.unilocal.dto.ConsultarNegocioDTO;
import co.edu.uniquindio.unilocal.dto.CrearLugarDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.enums.Estado;
import co.edu.uniquindio.unilocal.modelo.Lugar;
import co.edu.uniquindio.unilocal.repositorios.LugarRepo;
import co.edu.uniquindio.unilocal.servicios.interfaces.LugarServicio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LugarServicioImpl implements LugarServicio {

    private final LugarRepo lugarRepo;

    @Override
    public ResultadoDTO crearNegocio(CrearLugarDTO crearLugarDTO) {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        if(estaRepetidoNombreNegocio(crearLugarDTO.nombre())){
            resultadoDTO.setExitoso(false);
            resultadoDTO.getListaMensajesError().add("El nombre del negocio " + crearLugarDTO.nombre() +
                    " ya se encuentra en uso");
            return resultadoDTO;
        }

        Lugar lugar = new Lugar();
        lugar.setNombre(crearLugarDTO.nombre());
        lugar.setCategoriaLugar(crearLugarDTO.categoriaLugar());
        lugar.setTelefonos(crearLugarDTO.telefonos());
        lugar.setDescripcion(crearLugarDTO.descripcion());
        lugar.setUbicacion(crearLugarDTO.ubicacion());
        lugar.setHorarios(crearLugarDTO.horarioList());
        lugar.setImagenes(crearLugarDTO.urlFotos());
        lugar.setEstado(Estado.INACTIVO);

        lugarRepo.save(lugar);

 //       if(crearLugarDTO.eventoDTO() != null){
 //           EventoServicio eventoServicio;
 //           eventoServicio.crearEvento();
 //       }

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("Se creó correctamente el lugar");

        return resultadoDTO;
    }

    @Override
    public ResultadoDTO actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        Optional<Lugar> lugarOptional = lugarRepo.findById(actualizarNegocioDTO.id());

        if(lugarOptional.isEmpty()){
            throw new Exception("No se encuentra el lugar con id: " + actualizarNegocioDTO.id());
        }

        Lugar lugar = lugarOptional.get();

        lugar.setCategoriaLugar(actualizarNegocioDTO.categoriaLugar());
        lugar.setNombre(actualizarNegocioDTO.nombre());
        lugar.setDescripcion(actualizarNegocioDTO.descripcion());
        lugar.setUbicacion(actualizarNegocioDTO.ubicacion());
        lugar.setTelefonos(actualizarNegocioDTO.telefonos());
        lugar.setHorarios(actualizarNegocioDTO.horarios());
        lugar.setImagenes(actualizarNegocioDTO.imagenes());

        lugarRepo.save(lugar);

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("El lugar fue actualizado correctamente");

        return resultadoDTO;
    }

    @Override
    public ResultadoDTO eliminarNegocio(String idNegocio) throws Exception {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        Optional<Lugar> lugarOptional = lugarRepo.findById(idNegocio);

        if(lugarOptional.isEmpty()){
            throw new Exception("No existe el lugar");
        }

        lugarOptional.get().setEstado(Estado.INACTIVO);

        lugarRepo.save(lugarOptional.get());

        resultadoDTO.setExitoso(true);
        resultadoDTO.setMensaje("Se eliminó correctamente el lugar");

        return resultadoDTO;
    }

    @Override
    public ConsultarNegocioDTO buscarNegocios(String idLugar) {
        return null;
    }

    @Override
    public void filtrarPorEstado() {

    }

    @Override
    public List<Lugar> listarNegociosPropietario(String idUsuario) {
        return null;
    }

    @Override
    public void cambiarEstado() {

    }

    @Override
    public void registrarRevision() {

    }

    private boolean estaRepetidoNombreNegocio(@NotBlank @NotEmpty String nombreNegocio){
        return lugarRepo.findByNombre(nombreNegocio) != null;
    }
}
